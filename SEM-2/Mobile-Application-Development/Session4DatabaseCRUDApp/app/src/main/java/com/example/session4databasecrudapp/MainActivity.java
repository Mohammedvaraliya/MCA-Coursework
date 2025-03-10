package com.example.session4databasecrudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText rollnoEditText, emailEditText, nameEditText, passwordEditText, uTypeEditText;
    Button insertButton, showButton;
    TextView recordsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseHelper = new DatabaseHelper(this);

        rollnoEditText = findViewById(R.id.rollno);
        emailEditText = findViewById(R.id.email);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        uTypeEditText = findViewById(R.id.uType);
        insertButton = findViewById(R.id.insert_record_button);
        showButton = findViewById(R.id.show_record_button);
        recordsTextView = findViewById(R.id.recordsTextView);

        // Insert Record
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollno = rollnoEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String uType = uTypeEditText.getText().toString().trim();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || uType.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert record into the database
                boolean isInserted = databaseHelper.insertUser(rollno, email, name, password, uType);
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showButton.setOnClickListener(v -> {
            String records = databaseHelper.getAllUsers();
            if (records.isEmpty()) {
                recordsTextView.setText("No records found.");
            } else {
                recordsTextView.setText(records);
            }
        });
    }
}