package com.example.project.hotel.aiSystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.vertexai.FirebaseVertexAI;
import com.google.firebase.vertexai.GenerativeModel;
import com.google.firebase.vertexai.java.GenerativeModelFutures;
import com.google.firebase.vertexai.type.Content;
import com.google.firebase.vertexai.type.GenerateContentResponse;

public class AIMainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button sendButton;
    private TextView responseText;
    private GenerativeModelFutures model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aimain);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userInput = findViewById(R.id.user_input);
        sendButton = findViewById(R.id.send_button);
        responseText = findViewById(R.id.response_text);

        // Correct way to initialize model
        GenerativeModel gm = FirebaseVertexAI.getInstance()
                .generativeModel("gemini-2.0-flash");

        model = GenerativeModelFutures.from(gm);

        sendButton.setOnClickListener(v -> {
            String prompt = userInput.getText().toString().trim();
            if (!prompt.isEmpty()) {
                generateContent(prompt);
            } else {
                Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateContent(String prompt) {
        responseText.setText("Thinking...");

        // Create Content object as required
        Content content = new Content.Builder()
                .addText(prompt)
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(
                response,
                new FutureCallback<GenerateContentResponse>() {
                    @Override
                    public void onSuccess(GenerateContentResponse result) {
                        String output = result.getText();
                        runOnUiThread(() -> responseText.setText(output));
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        runOnUiThread(() -> {
                            responseText.setText("Error: " + t.getMessage());
                            Toast.makeText(AIMainActivity.this,
                                    "Error generating response: " + t.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        });
                    }
                },
                getMainExecutor()
        );
    }
}