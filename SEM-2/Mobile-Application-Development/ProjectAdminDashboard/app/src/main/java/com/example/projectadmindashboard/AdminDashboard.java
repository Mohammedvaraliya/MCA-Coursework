package com.example.projectadmindashboard;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button btnTables = findViewById(R.id.btnTables);
        Button btnOrders = findViewById(R.id.btnOrders);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnTables.setOnClickListener(v -> {
            startActivity(new Intent(this, TablesActivity.class));
        });

        btnOrders.setOnClickListener(v -> {
            startActivity(new Intent(this, OrderReportActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}