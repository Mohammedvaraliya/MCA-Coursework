package com.example.project.hotel.aiSystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private TextView responseText;
    private GenerativeModelFutures model;
    private DatabaseReference databaseRef;

    // Conversation state management
    private String currentConversationState = "WELCOME";
    private String selectedTable = "";
    private List<String> selectedItems = new ArrayList<>();
    private String currentSelectedItem = "";
    private int currentQuantity = 1;
    private int totalAmount = 0;

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
        responseText = findViewById(R.id.response_text);

        sendButton.setOnClickListener(v -> handleUserInput());
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
            case "MENU_SELECTION":
                handleMenuSelection(userMessage);
                break;
            case "ITEM_SELECTION":
                handleItemSelection(userMessage);
                break;
            case "QUANTITY_SELECTION":
                handleQuantitySelection(userMessage);
                break;
            case "ORDER_CONFIRMATION":
                handleOrderConfirmation(userMessage);
                break;
            default:
                handleDefaultState(userMessage);
        }
    }

    private void handleWelcomeState(String userMessage) {
        if (containsAny(userMessage, "table", "book")) {
            currentConversationState = "TABLE_SELECTION";
            fetchAvailableTables();
        } else if (containsAny(userMessage, "menu", "view")) {
            currentConversationState = "MENU_SELECTION";
            fetchMenuCategories();
        } else if (containsAny(userMessage, "order", "place")) {
            currentConversationState = "TABLE_SELECTION";
            displayAIResponse("First, let's select a table for your order. Checking available tables...");
            fetchAvailableTables();
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

    private void fetchAvailableTables() {
        databaseRef.child("Tables").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder tablesList = new StringBuilder("Available tables:\n\n");
                boolean hasAvailableTables = false;

                for (DataSnapshot tableSnapshot : snapshot.getChildren()) {
                    if ("available".equalsIgnoreCase(tableSnapshot.child("status").getValue(String.class))) {
                        String tableName = tableSnapshot.child("tableName").getValue(String.class);
                        int tableNumber = tableSnapshot.child("tableNumber").getValue(Integer.class);
                        tablesList.append("• ").append(tableName).append(" (Table ").append(tableNumber).append(")\n");
                        hasAvailableTables = true;
                    }
                }

                if (hasAvailableTables) {
                    displayAIResponse(tablesList + "\nPlease type the table number you'd like to book.");
                } else {
                    displayAIResponse("Sorry, all tables are currently occupied. Please try again later.");
                    currentConversationState = "WELCOME";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching tables. Please try again.");
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
                                        databaseRef.child("Tables").child(tableSnapshot.getKey())
                                                .child("status").setValue("occupied");

                                        displayAIResponse("Great! Table " + tableNumber + " (" + selectedTable +
                                                ") is now reserved.\nWould you like to see our menu? (yes/no)");
                                        currentConversationState = "MENU_SELECTION";
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
                StringBuilder menuCategories = new StringBuilder("Menu categories:\n\n");
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    menuCategories.append("• ").append(categorySnapshot.getKey()).append("\n");
                }
                displayAIResponse(menuCategories + "\nPlease type the category you're interested in.");
                currentConversationState = "ITEM_SELECTION";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching menu. Please try again.");
            }
        });
    }

    private void handleMenuSelection(String userMessage) {
        if (userMessage.equalsIgnoreCase("yes")) {
            fetchMenuCategories();
        } else if (userMessage.equalsIgnoreCase("no")) {
            displayAIResponse("Your table is reserved. Let us know if you need anything!");
            currentConversationState = "WELCOME";
        } else {
            displayAIResponse("Please answer with 'yes' or 'no'.");
        }
    }

    private void handleItemSelection(String userMessage) {
        currentSelectedItem = userMessage;
        displayAIResponse("How many " + userMessage + " would you like?");
        currentConversationState = "QUANTITY_SELECTION";
    }

    private void handleQuantitySelection(String userMessage) {
        try {
            currentQuantity = Integer.parseInt(userMessage);
            if (currentQuantity <= 0) throw new NumberFormatException();

            selectedItems.add(currentSelectedItem + "|" + currentQuantity);
            displayAIResponse("Added " + currentQuantity + " " + currentSelectedItem +
                    " to your order. Add more items? (yes/no)");
            currentConversationState = "MENU_SELECTION";
        } catch (NumberFormatException e) {
            displayAIResponse("Please enter a valid positive quantity.");
        }
    }

    private void handleOrderConfirmation(String userMessage) {
        if (userMessage.equalsIgnoreCase("yes")) {
            placeOrder();
        } else if (userMessage.equalsIgnoreCase("no")) {
            displayAIResponse("Okay, let me know if you want to make changes.");
        } else {
            displayAIResponse("Please answer with 'yes' or 'no'.");
        }
    }

    private void placeOrder() {
        if (TextUtils.isEmpty(selectedTable) || selectedItems.isEmpty()) {
            displayAIResponse("Please complete your order details first.");
            return;
        }

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String orderId = databaseRef.child("Orders").push().getKey();
        List<HashMap<String, Object>> orderItemsList = new ArrayList<>();

        databaseRef.child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot menuSnapshot) {
                boolean allItemsFound = true;
                int calculatedTotal = 0;

                for (String itemWithQuantity : selectedItems) {
                    String[] parts = itemWithQuantity.split("\\|");
                    if (parts.length != 2) continue;

                    String itemName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    boolean itemFound = false;

                    for (DataSnapshot categorySnapshot : menuSnapshot.getChildren()) {
                        for (DataSnapshot itemSnapshot : categorySnapshot.getChildren()) {
                            String currentItemName = itemSnapshot.child("itemName").getValue(String.class);
                            if (currentItemName != null && currentItemName.equalsIgnoreCase(itemName)) {
                                Integer price = itemSnapshot.child("price").getValue(Integer.class);
                                if (price != null) {
                                    HashMap<String, Object> itemMap = new HashMap<>();
                                    itemMap.put("name", currentItemName);
                                    itemMap.put("price", price);
                                    itemMap.put("quantity", quantity);
                                    orderItemsList.add(itemMap);
                                    calculatedTotal += price * quantity;
                                    itemFound = true;
                                    break;
                                }
                            }
                        }
                        if (itemFound) break;
                    }

                    if (!itemFound) {
                        allItemsFound = false;
                        displayAIResponse("Couldn't find item: " + itemName);
                        break;
                    }
                }

                if (allItemsFound) {
                    HashMap<String, Object> orderMap = new HashMap<>();
                    orderMap.put("items", orderItemsList);
                    orderMap.put("status", "pending");
                    orderMap.put("totalAmount", calculatedTotal);
                    orderMap.put("userEmail", email);
                    orderMap.put("tableName", selectedTable);
                    orderMap.put("timestamp", System.currentTimeMillis());

                    int finalCalculatedTotal = calculatedTotal;
                    databaseRef.child("Orders").child(orderId).setValue(orderMap)
                            .addOnSuccessListener(aVoid -> {
                                String formattedTotal = String.format("Total: \u20B9%d", finalCalculatedTotal);
                                displayAIResponse("Order placed successfully!\nID: " + orderId + "\n" + formattedTotal);

                                resetOrderState();
                            })
                            .addOnFailureListener(e -> {
                                displayAIResponse("Failed to place order: " + e.getMessage());
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error accessing menu: " + error.getMessage());
            }
        });
    }

    private void handleDefaultState(String userMessage) {
        Content content = new Content.Builder()
                .addText("You are a helpful hotel assistant. Answer this question: " + userMessage)
                .build();

        ListenableFuture<GenerateContentResponse> future = model.generateContent(content);

        // Provide an executor for callback
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
        selectedItems.clear();
        totalAmount = 0;
        currentSelectedItem = "";
        currentQuantity = 1;
        currentConversationState = "WELCOME";
    }

    private void displayAIResponse(String message) {
        responseText.append("\n\nAI: " + message);
        scrollToBottom();
    }

    private void displayUserMessage(String message) {
        responseText.append("\n\nYou: " + message);
        scrollToBottom();
    }

    private void scrollToBottom() {
        final int scrollAmount = responseText.getLayout().getLineTop(responseText.getLineCount()) -
                responseText.getHeight();
        if (scrollAmount > 0) {
            responseText.scrollTo(0, scrollAmount);
        }
    }

    private boolean containsAny(String input, String... terms) {
        for (String term : terms) {
            if (input.toLowerCase().contains(term.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}