package com.example.project.Session3AdvanceFeaturesApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;

public class Session3Activity3 extends AppCompatActivity {

    private Button send_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session33);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button dial = findViewById(R.id.dial);
        Button sw = findViewById(R.id.wallpaper);
        EditText mobile = findViewById(R.id.mobile);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dial_phone = new Intent(Intent.ACTION_DIAL);
                dial_phone.setData(Uri.parse("tel:" + mobile.getText().toString()));
                startActivity(dial_phone);
            }
        });

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent set = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(Intent.createChooser(set, "Select your wallpaper: "));
            }
        });

        send_email = findViewById(R.id.send_email_activity);
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Session3Activity3.this, Session3Activity4.class);
                startActivity(intent);
                finish();
            }
        });
    }
}