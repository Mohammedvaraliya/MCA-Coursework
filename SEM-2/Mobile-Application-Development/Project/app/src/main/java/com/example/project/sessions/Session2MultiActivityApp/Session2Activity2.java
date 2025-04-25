package com.example.project.Session2MultiActivityApp;

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

import com.example.project.R;

public class Session2Activity2 extends AppCompatActivity {

    Button backButton, search;
    TextView query, query2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session22);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.button_back);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", 0);
        String email = intent.getStringExtra("email");

        Toast.makeText(this, "User Id: " + userId + "\nEmail: " + email , Toast.LENGTH_LONG).show();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Session2Activity2.this, Session2Activity1.class);
                startActivity(intent);
                finish();
            }
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
                    Toast.makeText(Session2Activity2.this, "Please enter a search query or location.", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(googleSearch);
                finish();
            }
        });
    }
}