package com.example.project.hotel.aiSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.example.project.R;
import com.example.project.hotel.LoginSignupActivity;
import com.example.project.hotel.normalSystem.OrderReportActivity;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.vertexai.FirebaseVertexAI;
import com.google.firebase.vertexai.GenerativeModel;
import com.google.firebase.vertexai.java.GenerativeModelFutures;
import com.google.firebase.vertexai.type.Content;
import com.google.firebase.vertexai.type.GenerateContentResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AIMainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button sendButton;
    private GenerativeModelFutures model;
    private DatabaseReference databaseRef;

    // Conversation state management
    private String currentConversationState = "WELCOME";
    private String selectedTable = "";
    private String selectedTableNumber = "";
    private List<HashMap<String, Object>> selectedItems = new ArrayList<>();
    private String currentCategory = "";
    private int totalAmount = 0;
    private String currentSelectedItem = "";

    // Message types
    private static final int MESSAGE_TYPE_AI = 0;
    private static final int MESSAGE_TYPE_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aimain);

        initializeViews();
        initializeFirebase();
        initializeAIModel();
        sendInitialGreeting();
    }

    private void initializeViews() {
        userInput = findViewById(R.id.user_input);
        sendButton = findViewById(R.id.send_button);

        sendButton.setOnClickListener(v -> handleUserInput());
        userInput.setOnEditorActionListener((v, actionId, event) -> {
            handleUserInput();
            return true;
        });
    }

    private void initializeFirebase() {
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    private void initializeAIModel() {
        GenerativeModel gm = FirebaseVertexAI.getInstance()
                .generativeModel("gemini-1.5-flash");
        model = GenerativeModelFutures.from(gm);
    }

    private void sendInitialGreeting() {
        displayAIResponse("Hello! Welcome to our hotel. How may I assist you today?\n\n" +
                "You can:\n" +
                "1. Book a table\n" +
                "2. View menu items\n" +
                "3. Place an order\n" +
                "4. Check order status\n\n" +
                "What would you like to do?");
    }

    private void handleUserInput() {
        String userMessage = userInput.getText().toString().trim();
        if (!userMessage.isEmpty()) {
            displayUserMessage(userMessage);
            processUserInput(userMessage);
            userInput.setText("");
        } else {
            Toast.makeText(this, "Please enter your message", Toast.LENGTH_SHORT).show();
        }
    }

    private void processUserInput(String userMessage) {
        switch (currentConversationState) {
            case "WELCOME":
                handleWelcomeState(userMessage);
                break;
            case "TABLE_SELECTION":
                handleTableSelection(userMessage);
                break;
            case "MENU_CATEGORY_SELECTION":
                handleMenuCategorySelection(userMessage);
                break;
            case "MENU_ITEM_SELECTION":
                handleMenuItemSelection(userMessage);
                break;
            case "ITEM_QUANTITY_SELECTION":
                handleItemQuantitySelection(userMessage);
                break;
            case "ADD_MORE_ITEMS":
                handleAddMoreItems(userMessage);
                break;
            case "ORDER_CONFIRMATION":
                handleOrderConfirmation(userMessage);
                break;
            default:
                handleDefaultState(userMessage);
        }
    }

    private void handleWelcomeState(String userMessage) {
        if (containsAny(userMessage, "table", "book", "reserve")) {
            currentConversationState = "TABLE_SELECTION";
            showAvailableTables();
        } else if (containsAny(userMessage, "menu", "view", "show", "see")) {
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
        } else if (containsAny(userMessage, "order", "place")) {
            currentConversationState = "TABLE_SELECTION";
            displayAIResponse("First, let's select a table for your order. Showing available tables...");
            showAvailableTables();
        } else if (containsAny(userMessage, "status", "check")) {
            displayAIResponse("Please provide your order ID to check the status.");
        } else {
            displayAIResponse("I'm not sure what you're asking. Could you please clarify?\n\n" +
                    "You can:\n" +
                    "1. Book a table\n" +
                    "2. View menu items\n" +
                    "3. Place an order\n" +
                    "4. Check order status");
        }
    }

    private void showAvailableTables() {
        databaseRef.child("Tables").orderByChild("status").equalTo("available")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            StringBuilder htmlTable = new StringBuilder();
                            htmlTable.append("<html><head><style>")
                                    .append("body { font-family: Arial, sans-serif; }")
                                    .append(".table-container { margin: 10px; }")
                                    .append(".hotel-table { width: 100%; border-collapse: collapse; }")
                                    .append(".hotel-table th { background-color: #4CAF50; color: white; padding: 10px; text-align: left; }")
                                    .append(".hotel-table td, .hotel-table th { border: 1px solid #ddd; padding: 8px; }")
                                    .append(".hotel-table tr:nth-child(even) { background-color: #f2f2f2; }")
                                    .append(".hotel-table tr:hover { background-color: #ddd; }")
                                    .append(".select-btn { background-color: #4CAF50; color: white; border: none; padding: 5px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 12px; margin: 2px 1px; cursor: pointer; border-radius: 4px; }")
                                    .append("</style></head><body><div class='table-container'>")
                                    .append("<table class='hotel-table'><thead><tr>")
                                    .append("<th>Sr No.</th><th>Table No.</th><th>Table Name</th><th>Action</th>")
                                    .append("</tr></thead><tbody>");

                            int serialNo = 1;
                            for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                                int tableNumber = tableSnapshot.child("tableNumber").getValue(Integer.class);
                                String tableName = tableSnapshot.child("tableName").getValue(String.class);

                                htmlTable.append("<tr>")
                                        .append("<td>").append(serialNo++).append("</td>")
                                        .append("<td>").append(tableNumber).append("</td>")
                                        .append("<td>").append(tableName).append("</td>")
                                        .append("<td><button class='select-btn' onclick='Android.selectTable(").append(tableNumber).append(")'>Select</button></td>")
                                        .append("</tr>");
                            }

                            htmlTable.append("</tbody></table></div></body></html>");
                            displayHtmlResponse(htmlTable.toString(), "Available Tables", "table");
                        } else {
                            displayAIResponse("No available tables at the moment.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        displayAIResponse("Error fetching table data. Please try again.");
                    }
                });
    }

    private void handleTableSelection(String userMessage) {
        try {
            int tableNumber = Integer.parseInt(userMessage);
            databaseRef.child("Tables").orderByChild("tableNumber").equalTo(tableNumber)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                                    if ("available".equalsIgnoreCase(tableSnapshot.child("status").getValue(String.class))) {
                                        selectedTable = tableSnapshot.child("tableName").getValue(String.class);
                                        selectedTableNumber = String.valueOf(tableNumber);
                                        databaseRef.child("Tables").child(tableSnapshot.getKey())
                                                .child("status").setValue("occupied");

                                        displayAIResponse("Great! Table " + tableNumber + " (" + selectedTable +
                                                ") is now reserved.\nWould you like to see our menu? (yes/no)");
                                        currentConversationState = "MENU_CATEGORY_SELECTION";
                                        return;
                                    }
                                }
                                displayAIResponse("That table is not available. Please choose another.");
                            } else {
                                displayAIResponse("Invalid table number. Please try again.");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            displayAIResponse("Error processing your request. Please try again.");
                        }
                    });
        } catch (NumberFormatException e) {
            displayAIResponse("Please enter a valid table number.");
        }
    }

    private void fetchMenuCategories() {
        databaseRef.child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    StringBuilder htmlTable = new StringBuilder();
                    htmlTable.append("<html><head><style>")
                            .append("body { font-family: Arial, sans-serif; }")
                            .append(".table-container { margin: 10px; }")
                            .append(".hotel-table { width: 100%; border-collapse: collapse; }")
                            .append(".hotel-table th { background-color: #4CAF50; color: white; padding: 10px; text-align: left; }")
                            .append(".hotel-table td, .hotel-table th { border: 1px solid #ddd; padding: 8px; }")
                            .append(".hotel-table tr:nth-child(even) { background-color: #f2f2f2; }")
                            .append(".hotel-table tr:hover { background-color: #ddd; }")
                            .append(".select-btn { background-color: #4CAF50; color: white; border: none; padding: 5px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 12px; margin: 2px 1px; cursor: pointer; border-radius: 4px; }")
                            .append("</style></head><body><div class='table-container'>")
                            .append("<table class='hotel-table'><thead><tr>")
                            .append("<th>Sr No.</th><th>Menu Category</th><th>Action</th>")
                            .append("</tr></thead><tbody>");

                    int serialNo = 1;
                    for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                        String categoryName = categorySnapshot.getKey();
                        htmlTable.append("<tr>")
                                .append("<td>").append(serialNo++).append("</td>")
                                .append("<td>").append(categoryName).append("</td>")
                                .append("<td><button class='select-btn' onclick='Android.selectCategory(\"").append(categoryName).append("\")'>Select</button></td>")
                                .append("</tr>");
                    }

                    htmlTable.append("</tbody></table></div></body></html>");
                    displayHtmlResponse(htmlTable.toString(), "Menu Categories", "category");
                } else {
                    displayAIResponse("No menu categories available.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching menu categories. Please try again.");
            }
        });
    }

    private void handleMenuCategorySelection(String userMessage) {
        if (userMessage.equalsIgnoreCase("yes")) {
            fetchMenuCategories();
        } else if (userMessage.equalsIgnoreCase("no")) {
            if (selectedItems.isEmpty()) {
                displayAIResponse("Your table is reserved. Let us know if you need anything!");
                currentConversationState = "WELCOME";
            } else {
                confirmOrder();
            }
        } else {
            currentCategory = userMessage;
            fetchMenuItems(userMessage);
        }
    }

    private void fetchMenuItems(String category) {
        databaseRef.child("Menu").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    StringBuilder htmlTable = new StringBuilder();
                    htmlTable.append("<html><head><style>")
                            .append("body { font-family: Arial, sans-serif; }")
                            .append(".table-container { margin: 10px; }")
                            .append(".hotel-table { width: 100%; border-collapse: collapse; }")
                            .append(".hotel-table th { background-color: #4CAF50; color: white; padding: 10px; text-align: left; }")
                            .append(".hotel-table td, .hotel-table th { border: 1px solid #ddd; padding: 8px; }")
                            .append(".hotel-table tr:nth-child(even) { background-color: #f2f2f2; }")
                            .append(".hotel-table tr:hover { background-color: #ddd; }")
                            .append(".select-btn { background-color: #4CAF50; color: white; border: none; padding: 5px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 12px; margin: 2px 1px; cursor: pointer; border-radius: 4px; }")
                            .append("</style></head><body><div class='table-container'>")
                            .append("<table class='hotel-table'><thead><tr>")
                            .append("<th>Sr No.</th><th>Item Name</th><th>Price</th><th>Action</th>")
                            .append("</tr></thead><tbody>");

                    int serialNo = 1;
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        String itemName = itemSnapshot.child("itemName").getValue(String.class);
                        Integer price = itemSnapshot.child("price").getValue(Integer.class);

                        if (itemName != null && price != null) {
                            htmlTable.append("<tr>")
                                    .append("<td>").append(serialNo++).append("</td>")
                                    .append("<td>").append(itemName).append("</td>")
                                    .append("<td>₹").append(price).append("</td>")
                                    .append("<td><button class='select-btn' onclick='Android.selectItem(\"").append(itemName).append("\")'>Select</button></td>")
                                    .append("</tr>");
                        }
                    }

                    htmlTable.append("</tbody></table></div></body></html>");
                    displayHtmlResponse(htmlTable.toString(), "Menu Items: " + category, "item");
                } else {
                    displayAIResponse("No items available in this category.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching menu items. Please try again.");
            }
        });
    }

    private void handleMenuItemSelection(String userMessage) {
        currentSelectedItem = userMessage;
        displayAIResponse("How many " + userMessage + " would you like?");
        currentConversationState = "ITEM_QUANTITY_SELECTION";
    }

    private void handleItemQuantitySelection(String userMessage) {
        try {
            int quantity = Integer.parseInt(userMessage);
            if (quantity <= 0) {
                displayAIResponse("Please enter a positive number.");
                return;
            }

            databaseRef.child("Menu").child(currentCategory).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        String itemName = itemSnapshot.child("itemName").getValue(String.class);
                        if (itemName != null && itemName.equalsIgnoreCase(currentSelectedItem)) {
                            Integer price = itemSnapshot.child("price").getValue(Integer.class);
                            if (price != null) {
                                HashMap<String, Object> selectedItem = new HashMap<>();
                                selectedItem.put("name", itemName);
                                selectedItem.put("price", price);
                                selectedItem.put("quantity", quantity);
                                totalAmount += (price * quantity);
                                selectedItem.put("totalPrice", totalAmount);
                                selectedItems.add(selectedItem);

                                displayAIResponse(quantity + " " + itemName + " added to your order. " +
                                        "Would you like to add more items? (yes/no)");
                                currentConversationState = "ADD_MORE_ITEMS";
                                return;
                            }
                        }
                    }
                    displayAIResponse("Error processing your order. Please try again.");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    displayAIResponse("Error processing your order. Please try again.");
                }
            });
        } catch (NumberFormatException e) {
            displayAIResponse("Please enter a valid number.");
        }
    }

    private void handleAddMoreItems(String userMessage) {
        if (userMessage.equalsIgnoreCase("yes")) {
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
        } else if (userMessage.equalsIgnoreCase("no")) {
            confirmOrder();
        } else {
            displayAIResponse("Please answer with 'yes' or 'no'.");
        }
    }

    private void confirmOrder() {
        if (selectedItems.isEmpty()) {
            displayAIResponse("You haven't selected any items yet.");
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
            return;
        }

        StringBuilder orderSummary = new StringBuilder("Your order summary:\n\n");
        orderSummary.append("Table: ").append(selectedTable).append("\n\n");
        orderSummary.append("Items:\n");

        for (HashMap<String, Object> item : selectedItems) {
            orderSummary.append("• ")
                    .append(item.get("quantity"))
                    .append(" x ")
                    .append(item.get("name"))
                    .append(" - ₹")
                    .append((int)item.get("price") * (int)item.get("quantity"))
                    .append("\n");
        }

        orderSummary.append("\nTotal: ₹").append(totalAmount).append("\n\n");
        orderSummary.append("Would you like to place this order? (yes/no)");

        displayAIResponse(orderSummary.toString());
        currentConversationState = "ORDER_CONFIRMATION";
    }

    private void handleOrderConfirmation(String userMessage) {
        if (userMessage.equalsIgnoreCase("yes")) {
            placeOrder();
        } else if (userMessage.equalsIgnoreCase("no")) {
            displayAIResponse("Okay, let me know if you want to make changes.");
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
        } else {
            displayAIResponse("Please answer with 'yes' or 'no'.");
        }
    }

    private void placeOrder() {
        String email = FirebaseAuth.getInstance().getCurrentUser() != null ?
                FirebaseAuth.getInstance().getCurrentUser().getEmail() : "guest";

        String orderId = databaseRef.child("Orders").push().getKey();
        long orderDate = System.currentTimeMillis();

        HashMap<String, Object> order = new HashMap<>();
        order.put("timestamp", orderDate);
        order.put("items", selectedItems);
        order.put("totalAmount", totalAmount);
        order.put("status", "Pending");
        order.put("tableName", selectedTable);
        order.put("userEmail", email);

        databaseRef.child("Orders").child(orderId).setValue(order)
                .addOnSuccessListener(aVoid -> {
                    displayAIResponse("Order placed successfully!\nOrder ID: " + orderId +
                            "\nTotal: ₹" + totalAmount +
                            "\nStatus: Pending\n\nThank you for your order!");
                    resetOrderState();
                })
                .addOnFailureListener(e -> {
                    displayAIResponse("Failed to place order: " + e.getMessage());
                });
    }

    private void displayHtmlResponse(String htmlContent, String title, String type) {
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(webView)
                .setTitle(title)
                .setPositiveButton("Close", null)
                .show();

        switch (type) {
            case "table":
                webView.addJavascriptInterface(new WebAppInterface(dialog), "Android");
                break;
            case "category":
                webView.addJavascriptInterface(new CategoryWebInterface(dialog), "Android");
                break;
            case "item":
                webView.addJavascriptInterface(new ItemWebInterface(dialog), "Android");
                break;
        }
    }

    public class WebAppInterface {
        private AlertDialog dialog;

        public WebAppInterface(AlertDialog dialog) {
            this.dialog = dialog;
        }

        @JavascriptInterface
        public void selectTable(int tableNumber) {
            runOnUiThread(() -> {
                dialog.dismiss();
                handleTableSelection(String.valueOf(tableNumber));
            });
        }
    }

    public class CategoryWebInterface {
        private AlertDialog dialog;

        public CategoryWebInterface(AlertDialog dialog) {
            this.dialog = dialog;
        }

        @JavascriptInterface
        public void selectCategory(String category) {
            runOnUiThread(() -> {
                dialog.dismiss();
                currentCategory = category;
                displayAIResponse("You selected: " + category + "\nFetching items...");
                fetchMenuItems(category);
            });
        }
    }

    public class ItemWebInterface {
        private AlertDialog dialog;

        public ItemWebInterface(AlertDialog dialog) {
            this.dialog = dialog;
        }

        @JavascriptInterface
        public void selectItem(String itemName) {
            runOnUiThread(() -> {
                dialog.dismiss();
                handleMenuItemSelection(itemName);
            });
        }
    }

    private void handleDefaultState(String userMessage) {
        Content content = new Content.Builder()
                .addText("You are a helpful hotel assistant. Answer this question: " + userMessage)
                .build();

        ListenableFuture<GenerateContentResponse> future = model.generateContent(content);
        Executor executor = Executors.newSingleThreadExecutor();

        Futures.addCallback(future, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse response) {
                String output = response.getText();
                runOnUiThread(() -> displayAIResponse(output));
            }

            @Override
            public void onFailure(Throwable t) {
                runOnUiThread(() -> displayAIResponse("Sorry, I couldn't process your request. Please try again."));
            }
        }, executor);
    }

    private void resetOrderState() {
        selectedTable = "";
        selectedTableNumber = "";
        selectedItems.clear();
        totalAmount = 0;
        currentCategory = "";
        currentConversationState = "WELCOME";
    }

    private void displayAIResponse(String message) {
        addMessageToChat("AI Assistant", message, MESSAGE_TYPE_AI);
    }

    private void displayUserMessage(String message) {
        addMessageToChat("You", message, MESSAGE_TYPE_USER);
    }

    private void addMessageToChat(String sender, String message, int messageType) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View messageView = inflater.inflate(R.layout.message_item, findViewById(R.id.messages_container), false);

        ImageView icon = messageView.findViewById(R.id.message_icon);
        TextView senderText = messageView.findViewById(R.id.message_sender);
        TextView messageText = messageView.findViewById(R.id.message_text);

        if (messageType == MESSAGE_TYPE_AI) {
            icon.setImageResource(R.drawable.ic_ai);
            senderText.setTextColor(ContextCompat.getColor(this, R.color.ai_message_text));
            messageText.setTextColor(ContextCompat.getColor(this, R.color.ai_message_text));
            messageText.setBackgroundResource(R.drawable.ai_message_bg);
        } else {
            icon.setImageResource(R.drawable.ic_user);
            senderText.setTextColor(ContextCompat.getColor(this, R.color.user_message_text));
            messageText.setTextColor(ContextCompat.getColor(this, R.color.user_message_text));
            messageText.setBackgroundResource(R.drawable.user_message_bg);
        }

        senderText.setText(sender);
        messageText.setText(message);

        LinearLayout container = findViewById(R.id.messages_container);
        container.addView(messageView);

        final NestedScrollView scrollView = findViewById(R.id.chat_scroll);
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }

    private boolean containsAny(String input, String... terms) {
        input = input.toLowerCase();
        for (String term : terms) {
            if (input.contains(term.toLowerCase())) {
                return true;
            }
        }
        return false;
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