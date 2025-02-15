package com.example.session3advancefeaturesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SendEmail extends AppCompatActivity {

    EditText to, cc, subject, body;
    Button send, send_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SendEmail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        to = findViewById(R.id.recipient);
        cc = findViewById(R.id.cc);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.message);
        send = findViewById(R.id.send_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        send_email = findViewById(R.id.string_arrays_activity);
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendEmail.this, StringArrays.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendEmail() {
        String toEmail = to.getText().toString();
        String ccEmail = cc.getText().toString();
        String subjectText = subject.getText().toString();
        String bodyText = body.getText().toString();

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("message/rfc822");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
        mailIntent.putExtra(Intent.EXTRA_CC, new String[]{ccEmail});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectText);
        mailIntent.putExtra(Intent.EXTRA_TEXT, bodyText);

        startActivity(Intent.createChooser(mailIntent, "Select your Email App:"));
    }

}