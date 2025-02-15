package com.example.session3advancefeaturesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private TextView usernameTextView, emailTextView, passwordTextView, departmentTextView, query, query2;
    private Button search, dial_and_wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameTextView = findViewById(R.id.username_text);
        emailTextView = findViewById(R.id.email_text);
        passwordTextView = findViewById(R.id.password_text);
        departmentTextView = findViewById(R.id.department_text);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String department = intent.getStringExtra("department");

        usernameTextView.setText("Username: " + username);
        emailTextView.setText("Email: " + email);
        passwordTextView.setText("Password: " + password);
        departmentTextView.setText("Department: " + department);

        findViewById(R.id.back_button).setOnClickListener(view -> {
            finish();
        });

        query = findViewById(R.id.query);
        query2 = findViewById(R.id.query2);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleSearch = new Intent(Intent.ACTION_VIEW);
                if (!query.getText().toString().isEmpty()) {
                    googleSearch.setData(Uri.parse("https://www.google.com/search?q=" + query.getText().toString()));
                } else if (!query2.getText().toString().isEmpty()) {
                    googleSearch.setData(Uri.parse("geo:0,0?q=" + query2.getText().toString()));
                } else {
                    Toast.makeText(MainActivity2.this, "Please enter a search query or location.", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(googleSearch);
                finish();
            }
        });

        dial_and_wallpaper = findViewById(R.id.dial_and_wallpaper);
        dial_and_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, DialAndWallpaper.class);
                startActivity(intent);
                finish();
            }
        });
    }
}