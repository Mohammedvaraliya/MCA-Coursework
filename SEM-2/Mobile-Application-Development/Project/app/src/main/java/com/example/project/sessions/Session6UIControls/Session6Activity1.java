package com.example.project.sessions.Session6UIControls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class Session6Activity1 extends AppCompatActivity {

    private EditText nameEditText, dobEditText, commentEditText;
    private CheckBox[] checkBoxes;
    private RadioGroup radioGroup;
    private MultiAutoCompleteTextView cityEditText;
    private AutoCompleteTextView instituteEditText;
    private TextView outputTextView;
    private Button submitButton, goToOtherUIControlActivity;

    private static final String[] INSTITUTES = {
            "KJ Somaiya Institute of Management",
            "KJ Somaiya Engineering",
            "KJ Somaiya School of Design",
            "KJ Medical College"
    };

    private static final String[] CITIES = {
            "Mumbai", "Mangalore", "Pune", "Patna", "Chennai", "Kachchh"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session61);

        initializeUI();
        setupAutoCompleteFields();
        submitButton.setOnClickListener(v -> displayInput());


        goToOtherUIControlActivity = findViewById(R.id.go_to_activity2_button);

        goToOtherUIControlActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Session6Activity2.class);
                startActivity(intent);
            }
        });
    }

    private void initializeUI() {
        nameEditText = findViewById(R.id.name_edittext);
        dobEditText = findViewById(R.id.dob_edittext);
        instituteEditText = findViewById(R.id.institute_edittext);
        commentEditText = findViewById(R.id.comment_edittext);
        cityEditText = findViewById(R.id.city_edittext);
        radioGroup = findViewById(R.id.radioGroup);
        outputTextView = findViewById(R.id.output);
        submitButton = findViewById(R.id.submit);

        // Store checkboxes in an array for easy access
        checkBoxes = new CheckBox[]{
                findViewById(R.id.svu_checkBox),
                findViewById(R.id.ayurvihar_checkbox)
        };
    }

    private void setupAutoCompleteFields() {
        ArrayAdapter<String> instituteAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, INSTITUTES);
        instituteEditText.setAdapter(instituteAdapter);
        instituteEditText.setThreshold(1);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, CITIES);
        cityEditText.setAdapter(cityAdapter);
        cityEditText.setThreshold(1);
        cityEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void displayInput() {
        StringBuilder result = new StringBuilder();

        result.append("Name: ").append(nameEditText.getText().toString()).append("\n");

        result.append("Selected Checkboxes: ");
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                result.append(checkBox.getText()).append(" ");
            }
        }
        result.append("\n");

        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioId);
            result.append("Selected Category: ").append(selectedRadioButton.getText()).append("\n");
        }

        result.append("DOB: ").append(dobEditText.getText().toString()).append("\n");
        result.append("Institute: ").append(instituteEditText.getText().toString()).append("\n");
        result.append("City: ").append(cityEditText.getText().toString()).append("\n");
        result.append("Comment: ").append(commentEditText.getText().toString()).append("\n");

        outputTextView.setText(result.toString());
    }
}