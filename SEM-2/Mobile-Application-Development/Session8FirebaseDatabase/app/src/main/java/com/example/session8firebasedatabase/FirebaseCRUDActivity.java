package com.example.session8firebasedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.*;

public class FirebaseCRUDActivity extends AppCompatActivity {

    EditText uniqueIdEditText, emailEditText, nameEditText, passwordEditText;
    Spinner departmentSpinner, parentNodeSpinner;
    Button insertButton, showButton, searchButton, updateButton, deleteButton;
    TextView recordsTextView;
    AlertDialog.Builder alertDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    String selectedParentNode = "Students";

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

        uniqueIdEditText = findViewById(R.id.idEditText);
        emailEditText = findViewById(R.id.email);
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        departmentSpinner = findViewById(R.id.department);
        parentNodeSpinner = findViewById(R.id.parent_node_spinner);
        insertButton = findViewById(R.id.insert_record_button);
        showButton = findViewById(R.id.show_record_button);
        searchButton = findViewById(R.id.search_record_button);
        updateButton = findViewById(R.id.update_record_button);
        deleteButton = findViewById(R.id.delete_record_button);
        recordsTextView = findViewById(R.id.recordsTextView);

        alertDialog = new AlertDialog.Builder(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference(selectedParentNode);

        // Setup parent node spinner
        String[] parentNodes = {"Students", "Faculty", "Staff", "Alumni"};
        ArrayAdapter<String> parentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, parentNodes);
        parentNodeSpinner.setAdapter(parentAdapter);

        parentNodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedParentNode = parent.getItemAtPosition(position).toString();
                dbRef = firebaseDatabase.getReference(selectedParentNode);
                Toast.makeText(FirebaseCRUDActivity.this, "Now working with: " + selectedParentNode, Toast.LENGTH_SHORT).show();
                updateDepartmentSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        updateDepartmentSpinner();

        // Insert Record
        insertButton.setOnClickListener(v -> {
            String id = uniqueIdEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String dept = departmentSpinner.getSelectedItem().toString();

            if (id.isEmpty() || email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(this, "Record already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Object obj = createObject(id, email, name, password, dept);
                        dbRef.child(id).setValue(obj)
                                .addOnSuccessListener(aVoid -> Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(this, "Insertion Failed", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });

        // Show All Records
        showButton.setOnClickListener(v -> {
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        StringBuilder allData = new StringBuilder();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            allData.append(getRecordText(child)).append("\n\n");
                        }
                        alertDialog.setTitle("All Records");
                        alertDialog.setMessage(allData.toString());
                        alertDialog.setPositiveButton("OK", null);
                        alertDialog.show();
                    } else {
                        Toast.makeText(FirebaseCRUDActivity.this, "No records found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(FirebaseCRUDActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Search Specific Record
        searchButton.setOnClickListener(v -> {
            String id = uniqueIdEditText.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(this, "Enter ID to search", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        recordsTextView.setText(getRecordText(snapshot));
                    } else {
                        Toast.makeText(FirebaseCRUDActivity.this, "Record not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(FirebaseCRUDActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Update Record
        updateButton.setOnClickListener(v -> {
            String id = uniqueIdEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String dept = departmentSpinner.getSelectedItem().toString();

            if (id.isEmpty() || email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult().exists()) {
                    Object obj = createObject(id, email, name, password, dept);
                    dbRef.child(id).setValue(obj)
                            .addOnSuccessListener(aVoid -> Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show());
                } else {
                    Toast.makeText(this, "Record does not exist", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Delete Record
        deleteButton.setOnClickListener(v -> {
            String id = uniqueIdEditText.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(this, "Enter ID to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            dbRef.child(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult().exists()) {
                    dbRef.child(id).removeValue()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                recordsTextView.setText("");
                            })
                            .addOnFailureListener(e -> Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show());
                } else {
                    Toast.makeText(this, "Record not found", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void updateDepartmentSpinner() {
        String[] options;
        switch (selectedParentNode) {
            case "Faculty":
                options = new String[]{"Professor", "Associate Professor", "Assistant Professor", "Lecturer"};
                break;
            case "Staff":
                options = new String[]{"Admin", "Lab Assistant", "Clerk", "Security"};
                break;
            case "Alumni":
                options = new String[]{"2018", "2019", "2020", "2021", "2022", "2023"};
                break;
            default:
                options = new String[]{"MCA", "MBA", "HCM", "MBA in Finance", "MBA in HR", "MBA in Marketing"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        departmentSpinner.setAdapter(adapter);
    }

    private Object createObject(String id, String email, String name, String password, String department) {
        switch (selectedParentNode) {
            case "Faculty":
                return new Faculty(id, email, name, password, department);
            case "Staff":
                return new Staff(id, email, name, password, department);
            case "Alumni":
                return new Alumni(id, email, name, password, department);
            default:
                return new Student(id, email, name, password, department);
        }
    }

    private String getRecordText(DataSnapshot snapshot) {
        switch (selectedParentNode) {
            case "Faculty":
                Faculty f = snapshot.getValue(Faculty.class);
                return "ID: " + f.empId + "\nName: " + f.name + "\nEmail: " + f.email + "\nPassword: " + f.password + "\nDesignation: " + f.designation;
            case "Staff":
                Staff st = snapshot.getValue(Staff.class);
                return "ID: " + st.staffId + "\nName: " + st.name + "\nEmail: " + st.email + "\nPassword: " + st.password + "\nDepartment: " + st.department;
            case "Alumni":
                Alumni a = snapshot.getValue(Alumni.class);
                return "ID: " + a.alumniId + "\nName: " + a.name + "\nEmail: " + a.email + "\nPassword: " + a.password + "\nYear: " + a.passingYear;
            default:
                Student s = snapshot.getValue(Student.class);
                return "ID: " + s.rollno + "\nName: " + s.name + "\nEmail: " + s.email + "\nPassword: " + s.password + "\nDepartment: " + s.department;
        }
    }
}