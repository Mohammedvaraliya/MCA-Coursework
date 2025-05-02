package com.example.project.hotel.aiSystem;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VoiceAIActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

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

    // Voice components
    private TextToSpeech textToSpeech;
    private SpeechRecognizer speechRecognizer;
    private Button voiceButton;
    private TextView listeningStatus;
    private boolean isListening = false;

    // Message types
    private static final int MESSAGE_TYPE_AI = 0;
    private static final int MESSAGE_TYPE_USER = 1;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_voice_aiactivity);

            // Verify critical views exist
            if (findViewById(R.id.messages_container) == null) {
                throw new RuntimeException("messages_container not found in layout");
            }
            if (findViewById(R.id.chat_scroll) == null) {
                throw new RuntimeException("chat_scroll not found in layout");
            }

            initializeViews();
            initializeFirebase();
            initializeAIModel();
            initializeVoiceComponents();
            sendInitialGreeting();
        } catch (Exception e) {
            Log.e("VoiceAIActivity", "Initialization error", e);
            Toast.makeText(this, "App initialization failed", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initializeViews() {
        voiceButton = findViewById(R.id.voice_button);
        listeningStatus = findViewById(R.id.listening_status);

        voiceButton.setOnClickListener(v -> toggleVoiceRecognition());
    }

    private void initializeFirebase() {
        databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    private void initializeAIModel() {
        GenerativeModel gm = FirebaseVertexAI.getInstance()
                .generativeModel("gemini-1.5-flash");
        model = GenerativeModelFutures.from(gm);
    }

    private void initializeVoiceComponents() {
        // Initialize Text-to-Speech
        textToSpeech = new TextToSpeech(this, this);

        // Initialize Speech Recognizer
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    runOnUiThread(() -> {
                        listeningStatus.setVisibility(View.VISIBLE);
                        listeningStatus.setText("Listening...");
                    });
                }

                @Override
                public void onBeginningOfSpeech() {
                    runOnUiThread(() -> listeningStatus.setText("Listening..."));
                }

                @Override
                public void onRmsChanged(float rmsdB) {
                    // Optional: Visual feedback for voice input level
                }

                @Override
                public void onBufferReceived(byte[] buffer) {}

                @Override
                public void onEndOfSpeech() {
                    runOnUiThread(() -> listeningStatus.setText("Processing..."));
                }

                @Override
                public void onError(int error) {
                    runOnUiThread(() -> {
                        listeningStatus.setVisibility(View.GONE);
                        isListening = false;
                        voiceButton.setText("Tap to Speak");

                        if (error == SpeechRecognizer.ERROR_NO_MATCH) {
                            // More helpful message when no match found
                            switch (currentConversationState) {
                                case "ITEM_QUANTITY_SELECTION":
                                    displayAIResponse("I didn't catch the quantity. Please say a number between one and nine.");
                                    break;
                                case "MENU_CATEGORY_SELECTION":
                                    displayAIResponse("Please say the menu category you're interested in.");
                                    break;
                                case "MENU_ITEM_SELECTION":
                                    displayAIResponse("Please say the item name you'd like to order.");
                                    break;
                                case "ADD_MORE_ITEMS":
                                case "ORDER_CONFIRMATION":
                                    displayAIResponse("Please say yes or no.");
                                    break;
                                default:
                                    displayAIResponse("I didn't catch that. Could you please repeat?");
                            }
                        } else {
                            // Other error handling remains the same
                            String errorMessage;
                            switch (error) {
                                case SpeechRecognizer.ERROR_AUDIO:
                                    errorMessage = "Audio recording error";
                                    break;
                                case SpeechRecognizer.ERROR_CLIENT:
                                    errorMessage = "Client side error";
                                    break;
                                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                                    errorMessage = "Insufficient permissions";
                                    break;
                                case SpeechRecognizer.ERROR_NETWORK:
                                    errorMessage = "Network error";
                                    break;
                                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                                    errorMessage = "Network timeout";
                                    break;
                                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                                    errorMessage = "RecognitionService busy";
                                    break;
                                case SpeechRecognizer.ERROR_SERVER:
                                    errorMessage = "Server error";
                                    break;
                                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                                    errorMessage = "No speech input";
                                    break;
                                default:
                                    errorMessage = "Unknown error";
                            }
                            Toast.makeText(VoiceAIActivity.this,
                                    "Speech recognition error: " + errorMessage,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (matches != null && !matches.isEmpty()) {
                        String userMessage = matches.get(0);
                        displayUserMessage(userMessage);
                        processUserInput(userMessage);
                    }
                    runOnUiThread(() -> {
                        listeningStatus.setVisibility(View.GONE);
                        isListening = false;
                        voiceButton.setText("Tap to Speak");
                    });
                }

                @Override
                public void onPartialResults(Bundle partialResults) {}

                @Override
                public void onEvent(int eventType, Bundle params) {}
            });
        } else {
            Toast.makeText(this, "Speech recognition not available on this device", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleVoiceRecognition() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
            return;
        }

        if (!isListening) {
            startListening();
        } else {
            stopListening();
        }
    }

    private void startListening() {
        if (speechRecognizer != null) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");

            try {
                speechRecognizer.startListening(intent);
                isListening = true;
                voiceButton.setText("Listening...");
            } catch (Exception e) {
                Toast.makeText(this, "Error starting speech recognition", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void stopListening() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            isListening = false;
            voiceButton.setText("Tap to Speak");
            listeningStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.getDefault());
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Text-to-Speech initialization failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void sendInitialGreeting() {
        String greeting = "Hello! Welcome to our hotel. How may I assist you today? " +
                "You can: Book a table, View menu items, Place an order, or Check order status. " +
                "What would you like to do?";
        displayAIResponse(greeting);
        speak(greeting);
    }

    private void handleUserInput() {
        // This is now handled through voice recognition
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
            fetchAvailableTables();
        } else if (containsAny(userMessage, "menu", "view", "show", "see")) {
            currentConversationState = "MENU_CATEGORY_SELECTION";
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
                        tablesList.append("• Table ").append(tableNumber).append(" - ").append(tableName).append("\n");
                        hasAvailableTables = true;
                    }
                }

                if (hasAvailableTables) {
                    displayAIResponse(tablesList.toString() + "\nPlease say the table number you'd like to book.");
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
                                        selectedTableNumber = String.valueOf(tableNumber);
                                        databaseRef.child("Tables").child(tableSnapshot.getKey())
                                                .child("status").setValue("occupied");

                                        displayAIResponse("Great! Table " + tableNumber + " (" + selectedTable +
                                                ") is now reserved. Would you like to see our menu? Say yes or no.");
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
            displayAIResponse("Please say a valid table number.");
        }
    }

    private void fetchMenuCategories() {
        databaseRef.child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder menuCategories = new StringBuilder("Our menu categories:\n\n");
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    menuCategories.append("• ").append(categorySnapshot.getKey()).append("\n");
                }
                displayAIResponse(menuCategories.toString() + "\nPlease say the category you're interested in.");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching menu. Please try again.");
            }
        });
    }

    private void handleMenuCategorySelection(String userMessage) {
        if (isAffirmative(userMessage)) {
            fetchMenuCategories();
        } else if (isNegative(userMessage)) {
            if (selectedItems.isEmpty()) {
                displayAIResponse("Your table is reserved. Let us know if you need anything!");
                currentConversationState = "WELCOME";
            } else {
                confirmOrder();
            }
        } else {
            // More flexible category matching
            databaseRef.child("Menu").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String normalizedInput = userMessage.trim().toLowerCase();
                    String matchedCategory = null;

                    for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                        String category = categorySnapshot.getKey();
                        if (category != null &&
                                (category.toLowerCase().contains(normalizedInput) ||
                                        normalizedInput.contains(category.toLowerCase()))) {
                            matchedCategory = category;
                            break;
                        }
                    }

                    if (matchedCategory != null) {
                        currentCategory = matchedCategory;
                        fetchMenuItems(matchedCategory);
                    } else {
                        displayAIResponse("I didn't recognize that category. Please choose from: " +
                                getCategoryList(snapshot));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    displayAIResponse("Error checking menu. Please try again.");
                }
            });
        }
    }

    private String getCategoryList(DataSnapshot snapshot) {
        StringBuilder categories = new StringBuilder("\n");
        for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
            categories.append("• ").append(categorySnapshot.getKey()).append("\n");
        }
        return categories.toString();
    }

    private void fetchMenuItems(String category) {
        showLoadingIndicator(true);
        databaseRef.child("Menu").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder menuItems = new StringBuilder("Items in " + category + ":\n\n");
                List<String> itemNames = new ArrayList<>();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    String itemName = itemSnapshot.child("itemName").getValue(String.class);
                    Integer price = itemSnapshot.child("price").getValue(Integer.class);
                    if (itemName != null && price != null) {
                        menuItems.append("• ").append(itemName).append(" - ₹").append(price).append("\n");
                        itemNames.add(itemName.toLowerCase());
                    }
                }

                // Store available items for better recognition
                displayAIResponse(menuItems.toString() + "\nPlease say the item name you'd like to order.");
                currentConversationState = "MENU_ITEM_SELECTION";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error fetching menu items. Please try again.");
            }
        });
    }

    private void handleMenuItemSelection(String userMessage) {
        // Normalize the input (remove extra spaces, make lowercase)
        String normalizedInput = userMessage.trim().toLowerCase();

        databaseRef.child("Menu").child(currentCategory).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean itemFound = false;
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    String itemName = itemSnapshot.child("itemName").getValue(String.class);
                    if (itemName != null && itemName.toLowerCase().contains(normalizedInput)) {
                        itemFound = true;
                        Integer price = itemSnapshot.child("price").getValue(Integer.class);
                        if (price != null) {
                            currentSelectedItem = itemName;
                            displayAIResponse("How many " + itemName + " would you like? Say the quantity.");
                            currentConversationState = "ITEM_QUANTITY_SELECTION";
                            return;
                        }
                    }
                }

                if (!itemFound) {
                    displayAIResponse("Item not found. Please choose from the list above.");
                    // Show the menu items again
                    fetchMenuItems(currentCategory);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                displayAIResponse("Error checking menu item. Please try again.");
            }
        });
    }

    private void handleItemQuantitySelection(String userMessage) {
        int quantity = parseQuantity(userMessage);

        if (quantity <= 0) {
            displayAIResponse("Please say a valid quantity between one and nine for " + currentSelectedItem);
            return;
        }

        if (quantity > 9) {
            displayAIResponse("Maximum quantity is nine. Please say a smaller number.");
            return;
        }

        // Get the price for the selected item
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
                                    "Would you like to add more items?");
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
    }

    private void handleAddMoreItems(String userMessage) {
        if (isAffirmative(userMessage)) {
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
        } else if (isNegative(userMessage)) {
            confirmOrder();
        } else {
            displayAIResponse("Please say yes or no.");
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
        orderSummary.append("Would you like to place this order? Say yes or no.");

        displayAIResponse(orderSummary.toString());
        currentConversationState = "ORDER_CONFIRMATION";
    }

    private void handleOrderConfirmation(String userMessage) {
        if (isAffirmative(userMessage)) {
            placeOrder();
        } else if (isNegative(userMessage)) {
            displayAIResponse("Okay, let me know if you want to make changes.");
            currentConversationState = "MENU_CATEGORY_SELECTION";
            fetchMenuCategories();
        } else {
            displayAIResponse("Please say yes or no.");
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
                    String successMessage = "Order placed successfully!\nOrder ID: " + orderId +
                            "\nTotal: ₹" + totalAmount +
                            "\nStatus: Pending\n\nThank you for your order!";
                    displayAIResponse(successMessage);
                    resetOrderState();
                })
                .addOnFailureListener(e -> {
                    displayAIResponse("Failed to place order: " + e.getMessage());
                });
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
        currentSelectedItem = "";
        currentConversationState = "WELCOME";
    }

    private void displayAIResponse(String message) {
        addMessageToChat("AI Assistant", message, MESSAGE_TYPE_AI);
        speak(message);
    }

    private void displayUserMessage(String message) {
        addMessageToChat("You", message, MESSAGE_TYPE_USER);
    }

    private void addMessageToChat(String sender, String message, int messageType) {
        runOnUiThread(() -> {
            try {
                LayoutInflater inflater = LayoutInflater.from(this);
                View messageView = inflater.inflate(R.layout.message_item, findViewById(R.id.messages_container), false);

                ImageView icon = messageView.findViewById(R.id.message_icon);
                TextView senderText = messageView.findViewById(R.id.message_sender);
                TextView messageText = messageView.findViewById(R.id.message_text);
                LinearLayout messageContainer = messageView.findViewById(R.id.message_container);
                ConstraintLayout messageRoot = messageView.findViewById(R.id.message_root);

                // Set icon and colors based on message type
                if (messageType == MESSAGE_TYPE_AI) {
                    icon.setImageResource(R.drawable.ic_ai);
                    senderText.setTextColor(ContextCompat.getColor(this, R.color.ai_message_text));
                    messageText.setBackgroundResource(R.drawable.ai_message_bg);
                    messageText.setTextColor(ContextCompat.getColor(this, R.color.ai_message_text));

                    // Align AI messages to left
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) messageContainer.getLayoutParams();
                    params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                    params.endToEnd = ConstraintLayout.LayoutParams.UNSET;
                    messageContainer.setLayoutParams(params);
                } else {
                    icon.setImageResource(R.drawable.ic_user);
                    senderText.setTextColor(ContextCompat.getColor(this, R.color.user_message_text));
                    messageText.setBackgroundResource(R.drawable.user_message_bg);
                    messageText.setTextColor(ContextCompat.getColor(this, R.color.user_message_text));

                    // Align user messages to right
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) messageContainer.getLayoutParams();
                    params.startToStart = ConstraintLayout.LayoutParams.UNSET;
                    params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                    messageContainer.setLayoutParams(params);
                }

                senderText.setText(sender);
                messageText.setText(message);

                // Add the message to the container
                LinearLayout container = findViewById(R.id.messages_container);
                container.addView(messageView);

                // Scroll to bottom
                final NestedScrollView scrollView = findViewById(R.id.chat_scroll);
                scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
            } catch (Exception e) {
                Log.e("VoiceAIActivity", "Error adding message to chat", e);
                Toast.makeText(this, "Error displaying message", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void clearChat() {
        runOnUiThread(() -> {
            LinearLayout container = findViewById(R.id.messages_container);
            container.removeAllViews();
        });
    }

    private void showLoadingIndicator(boolean show) {
        runOnUiThread(() -> {
            if (show) {
                displayAIResponse("Processing your request...");
            }
        });
    }

    private boolean isAffirmative(String input) {
        String normalized = input.toLowerCase().trim();
        return normalized.matches("(y(a|e|i|o|u|w|v|h)?(a|e|i|o|u)?s+.*)") ||
                normalized.equals("y") ||
                normalized.equals("yeah") ||
                normalized.equals("yep") ||
                normalized.equals("sure") ||
                normalized.equals("ok");
    }

    private boolean isNegative(String input) {
        String normalized = input.toLowerCase().trim();
        return normalized.matches("n(a|e|i|o|u|w|v|h)?(a|e|i|o|u)?.*") ||
                normalized.equals("no") ||
                normalized.equals("nope") ||
                normalized.equals("nah") ||
                normalized.equals("not");
    }

    private int parseQuantity(String input) {
        // First try direct number conversion
        try {
            return Integer.parseInt(input.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            // If not a direct number, try word matching
            String normalized = input.toLowerCase()
                    .replaceAll("(one|won|wun|wan|van|vun)", "1")
                    .replaceAll("(two|to|too|tu|tou)", "2")
                    .replaceAll("(three|tree|thri|thre)", "3")
                    .replaceAll("(four|for|fore|foor|fou)", "4")
                    .replaceAll("(five|fife|fiv|fiv)", "5")
                    .replaceAll("(six|sicks|sik|siks)", "6")
                    .replaceAll("(seven|sevn|sevn)", "7")
                    .replaceAll("(eight|ate|ait|eit)", "8")
                    .replaceAll("(nine|nain|nin|nien)", "9")
                    .replaceAll("(zero|hero|ziro|ziro)", "0")
                    .replaceAll("[^0-9]", "");

            try {
                return Integer.parseInt(normalized);
            } catch (NumberFormatException ex) {
                return -1; // Invalid
            }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toggleVoiceRecognition();
            } else {
                Toast.makeText(this, "Permission denied to record audio", Toast.LENGTH_SHORT).show();
            }
        }
    }
}