package com.example.projectadmindashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private static final String ADMIN_EMAIL = "varacompany12@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!userEmail.equals(ADMIN_EMAIL)) {
                Toast.makeText(MainActivity.this, "Only admin can login", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null && user.getEmail().equals(ADMIN_EMAIL)) {
                                Toast.makeText(MainActivity.this, "Admin Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, AdminDashboard.class));
                                finish();
                            } else {
                                firebaseAuth.signOut();
                                Toast.makeText(MainActivity.this, "Only admin can login", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null && currentUser.getEmail().equals(ADMIN_EMAIL)) {
            startActivity(new Intent(this, AdminDashboard.class));
            finish();
        }
    }
}