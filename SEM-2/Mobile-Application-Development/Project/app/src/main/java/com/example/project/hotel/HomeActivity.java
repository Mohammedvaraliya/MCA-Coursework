package com.example.project.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import com.example.project.hotel.aiSystem.AIMainActivity;
import com.example.project.hotel.normalSystem.NormalMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    TextView titleTextView;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    Button normalHotelButton, aiHotelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Initialize
        titleTextView = findViewById(R.id.title);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        normalHotelButton = findViewById(R.id.normalHotelButton);
        aiHotelButton = findViewById(R.id.aiHotelButton);

        // Check if user exists and update welcome message
        if (currentUser != null) {
            String email = currentUser.getEmail();
            titleTextView.setText("Welcome, " + email + "!");
        } else {
            titleTextView.setText("Welcome, Guest!");
        }

        // Button Click Listeners (Optional)
        normalHotelButton.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Normal Hotel Management selected", Toast.LENGTH_SHORT).show();
            Intent normalMainIntent = new Intent(this, NormalMainActivity.class);
            startActivity(normalMainIntent);
            finish();
        });

        aiHotelButton.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "AI Hotel Management selected", Toast.LENGTH_SHORT).show();
            Intent aiMainIntent = new Intent(this, AIMainActivity.class);
            startActivity(aiMainIntent);
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginSignupActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}