package com.example.project.hotel.normalSystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.hotel.LoginSignupActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuSelectionActivity extends AppCompatActivity {

    private AutoCompleteTextView categoryDropdown, itemsDropdown, quantityDropdown;
    private MaterialButton addItemButton, placeOrderButton, clearSelectionButton;
    private TextView selectedItemsList, totalAmount, selectedItemsLabel, selectedTable;
    private MaterialCardView itemSelectionCard, selectedItemsCard;
    private List<OrderItem> orderItems = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> menu = new HashMap<>();
    private int total = 0;
    private String tableName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);

        // Initialize views
        selectedTable = findViewById(R.id.selectedTable);
        TextInputLayout categoryDropdownLayout = findViewById(R.id.categoryDropdownLayout);
        TextInputLayout itemsDropdownLayout = findViewById(R.id.itemsDropdownLayout);
        TextInputLayout quantityDropdownLayout = findViewById(R.id.quantityDropdownLayout);
        categoryDropdown = findViewById(R.id.categoryDropdown);
        itemsDropdown = findViewById(R.id.itemsDropdown);
        quantityDropdown = findViewById(R.id.quantityDropdown);
        addItemButton = findViewById(R.id.addItemButton);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        clearSelectionButton = findViewById(R.id.clearSelectionButton);
        selectedItemsList = findViewById(R.id.selectedItemsList);
        selectedItemsLabel = findViewById(R.id.selectedItemsLabel);
        totalAmount = findViewById(R.id.totalAmount);
        itemSelectionCard = findViewById(R.id.itemSelectionCard);
        selectedItemsCard = findViewById(R.id.selectedItemsCard);

        // Get table name from intent
        tableName = getIntent().getStringExtra("tableName");
        selectedTable.setText("Table: " + tableName);

        // Setup quantity dropdown
        setupQuantityDropdown();

        // Load menu from Firebase
        loadMenuFromFirebase();

        // Set click listeners
        addItemButton.setOnClickListener(v -> addItem());
        placeOrderButton.setOnClickListener(v -> placeOrder());
        clearSelectionButton.setOnClickListener(v -> clearSelection());
    }

    private void setupQuantityDropdown() {
        List<String> quantities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            quantities.add(String.valueOf(i));
        }

        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_item,
                quantities
        );
        quantityDropdown.setAdapter(quantityAdapter);
        quantityDropdown.setText(quantities.get(0), false); // Set default to 1
    }

    private void loadMenuFromFirebase() {
        DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference("Menu");

        menuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menu.clear();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    String categoryName = categorySnapshot.getKey();
                    HashMap<String, Integer> items = new HashMap<>();

                    for (DataSnapshot itemSnapshot : categorySnapshot.getChildren()) {
                        String itemName = itemSnapshot.child("itemName").getValue(String.class);
                        Integer price = itemSnapshot.child("price").getValue(Integer.class);

                        if (itemName != null && price != null) {
                            items.put(itemName, price);
                        }
                    }
                    menu.put(categoryName, items);
                }

                if (!menu.isEmpty()) {
                    setupCategoryDropdown();
                    // Clear any previous selections
                    categoryDropdown.setText("", false);
                    itemsDropdown.setText("", false);
                    quantityDropdown.setText("1", false);
                } else {
                    Toast.makeText(MenuSelectionActivity.this, "No menu items available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MenuSelectionActivity.this, "Failed to load menu.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupCategoryDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_item,
                new ArrayList<>(menu.keySet())
        );
        categoryDropdown.setAdapter(adapter);

        // Clear items dropdown when category changes
        categoryDropdown.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = (String) parent.getItemAtPosition(position);
            itemsDropdown.setText(""); // Clear previous selection
            updateItemsDropdown(selectedCategory);
        });

        // Also handle when user types and selects (if needed)
        categoryDropdown.setOnDismissListener(() -> {
            String selectedCategory = categoryDropdown.getText().toString();
            if (menu.containsKey(selectedCategory)) {
                itemsDropdown.setText(""); // Clear previous selection
                updateItemsDropdown(selectedCategory);
            }
        });
    }

    private void updateItemsDropdown(String category) {
        HashMap<String, Integer> itemsMap = menu.get(category);
        if (itemsMap != null && !itemsMap.isEmpty()) {
            List<String> itemNames = new ArrayList<>(itemsMap.keySet());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.dropdown_menu_item,
                    itemNames
            );
            itemsDropdown.setAdapter(adapter);

            // Show the item selection card
            itemSelectionCard.setVisibility(View.VISIBLE);

            // Reset quantity to default
            quantityDropdown.setText("1", false);
        } else {
            itemsDropdown.setAdapter(null);
            Toast.makeText(this, "No items available in this category", Toast.LENGTH_SHORT).show();
        }
    }

    private void addItem() {
        String selectedCategory = categoryDropdown.getText().toString();
        String selectedItem = itemsDropdown.getText().toString();
        String quantityText = quantityDropdown.getText().toString();

        // Validate category selection
        if (!menu.containsKey(selectedCategory)) {
            Toast.makeText(this, "Please select a valid category", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate item selection
        if (selectedItem.isEmpty() || !menu.get(selectedCategory).containsKey(selectedItem)) {
            Toast.makeText(this, "Please select a valid item", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantityText.isEmpty()) {
            quantityText = "1";
            quantityDropdown.setText(quantityText);
        }

        if (selectedItem.isEmpty()) {
            Toast.makeText(this, "Please select an item", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantityText.isEmpty()) {
            quantityText = "1";
            quantityDropdown.setText(quantityText);
        }

        int quantity = Integer.parseInt(quantityText);
        int price = menu.get(selectedCategory).get(selectedItem);
        int itemTotal = price * quantity;

        // Check if item already exists in order
        boolean itemExists = false;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getName().equals(selectedItem)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                orderItem.setTotalPrice(orderItem.getTotalPrice() + itemTotal);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            orderItems.add(new OrderItem(selectedItem, price, quantity, itemTotal));
        }

        total += itemTotal;
        updateOrderUI();

        // Show the selected items card
        selectedItemsCard.setVisibility(View.VISIBLE);
    }

    private void updateOrderUI() {
        StringBuilder builder = new StringBuilder();
        for (OrderItem item : orderItems) {
            builder.append("🍽️  <b>").append(item.getName()).append("</b>")
                    .append(" <small>(x").append(item.getQuantity()).append(")</small>")
                    .append("<br><font color='#4299E1'>₹").append(item.getTotalPrice()).append("</font>")
                    .append("<br><br>");
        }
        selectedItemsList.setText(Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT));
        totalAmount.setText(Html.fromHtml("<b>Total: </b><font color='#2D3748'>₹" + total + "</font>", Html.FROM_HTML_MODE_COMPACT));

        // Make the entire card visible (which includes the buttons)
        selectedItemsCard.setVisibility(View.VISIBLE);
    }

    private void clearSelection() {
        orderItems.clear();
        total = 0;
        selectedItemsList.setText("");
        selectedItemsCard.setVisibility(View.GONE);
    }

    private void placeOrder() {
        if (orderItems.isEmpty()) {
            Toast.makeText(this, "Please add items to your order", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String tableName = getIntent().getStringExtra("tableName");

        if (tableName == null || tableName.isEmpty()) {
            Toast.makeText(this, "Table name is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        Order order = new Order(
                email,
                tableName,
                new ArrayList<>(orderItems),
                total,
                "pending",
                System.currentTimeMillis()
        );

        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
        String orderId = ordersRef.push().getKey();

        if (orderId != null) {
            ordersRef.child(orderId).setValue(order)
                    .addOnSuccessListener(unused -> {
                        DatabaseReference tablesRef = FirebaseDatabase.getInstance().getReference("Tables");

                        tablesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                boolean matchFound = false;
                                for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                                    String dbTableName = tableSnapshot.child("tableName").getValue(String.class);
                                    if (dbTableName != null && dbTableName.equalsIgnoreCase(tableName)) {
                                        matchFound = true;
                                        tableSnapshot.getRef().child("status").setValue("occupied")
                                                .addOnSuccessListener(v -> {
                                                    Toast.makeText(MenuSelectionActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(MenuSelectionActivity.this, SuccessOrderActivity.class);
                                                    intent.putExtra("orderId", orderId);
                                                    startActivity(intent);
                                                    finish();
                                                })
                                                .addOnFailureListener(e ->
                                                        Toast.makeText(MenuSelectionActivity.this, "Failed to update table status: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                                        break;
                                    }
                                }

                                if (!matchFound) {
                                    Toast.makeText(MenuSelectionActivity.this, "Table not found in database", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MenuSelectionActivity.this, "Failed to access tables: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Failed to place order: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
            return true;
        } else if (id == R.id.action_show_orders) {
            startActivity(new Intent(this, OrderReportActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}