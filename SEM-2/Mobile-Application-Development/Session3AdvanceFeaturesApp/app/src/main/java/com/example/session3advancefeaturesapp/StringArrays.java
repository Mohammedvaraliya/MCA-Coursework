package com.example.session3advancefeaturesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StringArrays extends AppCompatActivity {

    private TextView resultTextView;
    private Button showDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_string_arrays);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.StringArrayas), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTextView = findViewById(R.id.result);
        showDataButton = findViewById(R.id.show_data);

        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }

    private void showData() {
        // Retrieve the string array from resources
        String[] fruitsArray = getResources().getStringArray(R.array.countries);

        StringBuilder stringBuilder = new StringBuilder();

        for (String fruit : fruitsArray) {
            stringBuilder.append(fruit).append("\n");
        }

        resultTextView.setText(stringBuilder.toString());

    }
}