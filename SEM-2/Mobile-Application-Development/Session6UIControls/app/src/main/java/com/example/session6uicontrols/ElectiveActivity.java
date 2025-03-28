package com.example.session6uicontrols;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ElectiveActivity extends AppCompatActivity {

    Button electives;
    AlertDialog.Builder electivesBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elective);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        electivesBuilder = new AlertDialog.Builder(this);
        electives = findViewById(R.id.electives);

        electives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String electiveList[] = {
                        "Economics",
                        "Natural Language Processing",
                        "Organizational Behaviour",
                        "Foreign Languages",
                        "Performing Arts"
                };

                electivesBuilder.setTitle("Select Your Elective Subject");

                electivesBuilder.setSingleChoiceItems(electiveList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ElectiveActivity.this, "You Selected: " + electiveList[which], Toast.LENGTH_SHORT).show();
                    }
                });

                electivesBuilder.create();
                electivesBuilder.show();
            }
        });
    }
}