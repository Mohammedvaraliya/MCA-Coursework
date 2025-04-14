package com.example.session8firebasedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseCRUDActivity extends AppCompatActivity {

    EditText rollnoEditText, emailEditText, nameEditText, passwordEditText;
    Spinner departmentSpinner;
    Button insertButton, showButton, searchButton, updateButton, deleteButton;
    TextView recordsTextView;
    AlertDialog.Builder alertDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_firebase_crudactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rollnoEditText = findViewById(R.id.rollno);
        emailEditText = findViewById(R.id.email);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        departmentSpinner = findViewById(R.id.department);
        insertButton = findViewById(R.id.insert_record_button);
        showButton = findViewById(R.id.show_record_button);
        searchButton = findViewById(R.id.search_record_button);
        updateButton = findViewById(R.id.update_record_button);
        deleteButton = findViewById(R.id.delete_record_button);
        recordsTextView = findViewById(R.id.recordsTextView);

        alertDialog = new AlertDialog.Builder(this);

        // Spinner view for Department
        String[] departments = {"MCA", "MBA", "HCM", "MBA in Finance", "MBA in HR", "MBA in Marketing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(adapter);

        // Firebase Database Instance
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("Students");

        // Insert Record
        insertButton.setOnClickListener(v -> {
            String rollNo = rollnoEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String department = departmentSpinner.getSelectedItem().toString();

            if (rollNo.isEmpty() || email.isEmpty() || name.isEmpty() || password.isEmpty() || department.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(rollNo, email, name, password, department);
            dbRef.child(rollNo).setValue(student)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Record Inserted Successfully!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to Insert Record!", Toast.LENGTH_SHORT).show());
        });

        // Show All Records
        showButton.setOnClickListener(v -> {
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        StringBuilder data = new StringBuilder();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Student s = child.getValue(Student.class);
                            data.append("Roll No: ").append(s.rollno).append("\n")
                                    .append("Name: ").append(s.name).append("\n")
                                    .append("Email: ").append(s.email).append("\n")
                                    .append("Password: ").append(s.password).append("\n")
                                    .append("Department: ").append(s.department).append("\n\n");
                        }
                        alertDialog.setTitle("All Student Records");
                        alertDialog.setMessage(data.toString());
                        alertDialog.setPositiveButton("OK", null);
                        alertDialog.show();
                    } else {
                        Toast.makeText(FirebaseCRUDActivity.this, "No Records Found!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(FirebaseCRUDActivity.this, "Failed to Fetch Data", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Search Specific Record
        searchButton.setOnClickListener(v -> {
            String rollNo = rollnoEditText.getText().toString().trim();

            if (rollNo.isEmpty()) {
                Toast.makeText(this, "Please enter Roll No to search", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(rollNo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Student s = snapshot.getValue(Student.class);
                        recordsTextView.setText("Roll No: " + s.rollno + "\nName: " + s.name + "\nEmail: " + s.email + "\nPassword: " + s.password + "\nUser Type: " + s.department);
                    } else {
                        Toast.makeText(FirebaseCRUDActivity.this, "Record Not Found!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(FirebaseCRUDActivity.this, "Failed to Search Data", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Update Record
        updateButton.setOnClickListener(v -> {
            String rollNo = rollnoEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String department = departmentSpinner.getSelectedItem().toString();

            if (rollNo.isEmpty() || email.isEmpty() || name.isEmpty() || password.isEmpty() || department.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(rollNo, email, name, password, department);
            dbRef.child(rollNo).setValue(student)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Record Updated Successfully!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to Update Record!", Toast.LENGTH_SHORT).show());
        });

        // Delete Record
        deleteButton.setOnClickListener(v -> {
            String rollNo = rollnoEditText.getText().toString().trim();

            if (rollNo.isEmpty()) {
                Toast.makeText(this, "Please enter Roll No to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(rollNo).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Record Deleted Successfully!", Toast.LENGTH_SHORT).show();
                        recordsTextView.setText("");
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to Delete Record!", Toast.LENGTH_SHORT).show());
        });
    }
}