package com.example.project.sessions.Session4DatabaseCRUDApp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;

public class Session4Activity1 extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    EditText rollnoEditText, emailEditText, nameEditText, passwordEditText, uTypeEditText;
    Button insertButton, showButton, searchButton, updateButton, deleteButton;
    TextView recordsTextView;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session41);
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
        searchButton = findViewById(R.id.search_record_button);
        updateButton = findViewById(R.id.update_record_button);
        deleteButton = findViewById(R.id.delete_record_button);
        recordsTextView = findViewById(R.id.recordsTextView);

        alertDialog = new AlertDialog.Builder(this);

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
                    Toast.makeText(Session4Activity1.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert record into the database
                boolean isInserted = databaseHelper.insertUser(rollno, email, name, password, uType);
                if (isInserted) {
                    Toast.makeText(Session4Activity1.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Session4Activity1.this, "User registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showButton.setOnClickListener(v -> {
            String records = databaseHelper.getAllUsers();
            if (records.isEmpty()) {
                recordsTextView.setText("No records found.");
            } else {
                // recordsTextView.setText(records);
                alertDialog.setTitle("All User Records");
                alertDialog.setMessage(records);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.create();
                alertDialog.show();
            }
        });

        searchButton.setOnClickListener(v -> {
            String rollno = rollnoEditText.getText().toString().trim();

            if (rollno.isEmpty()) {
                recordsTextView.setText("Please enter a roll number to search a record.");
                return;
            }

            String record = databaseHelper.searchRecord(rollno);
            recordsTextView.setText(record);
        });

        updateButton.setOnClickListener(v -> {
            String rollno = rollnoEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String uType = uTypeEditText.getText().toString().trim();

            if (rollno.isEmpty()) {
                Toast.makeText(Session4Activity1.this, "Roll number is required!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isUpdated = databaseHelper.updateRecord(rollno, email, name, password, uType);

            if (isUpdated) {
                Toast.makeText(Session4Activity1.this, "Record updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Session4Activity1.this, "Record not found!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            String rollno = rollnoEditText.getText().toString().trim();

            if (rollno.isEmpty()) {
                recordsTextView.setText("Please enter a roll number to delete a record.");
                return;
            }

            boolean isDeleted = databaseHelper.deleteUser(rollno);
            if (isDeleted) {
                recordsTextView.setText("Record deleted successfully.");
            } else {
                recordsTextView.setText("No record found with that Roll Number.");
            }
        });
    }
}