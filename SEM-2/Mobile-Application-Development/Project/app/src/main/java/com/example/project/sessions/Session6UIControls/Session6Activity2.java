package com.example.project.sessions.Session6UIControls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;

public class Session6Activity2 extends AppCompatActivity {

    ListView listView;
    Button showButton, goToElectivePage;
    Spinner spinnerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session62);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listview);
        showButton = findViewById(R.id.show_list_view);
        spinnerView = findViewById(R.id.spinner_view);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // List View Setup
                String subjectList[] = {
                        "Mobile Application Development",
                        "Machine Learning",
                        "Data Structures and Algorithms",
                        "Dynamic Programming",
                        "Core Java",
                        "DevOps"
                };

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, subjectList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedSubject = (String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(), "Your selected: " + selectedSubject, Toast.LENGTH_SHORT).show();
                    }
                });

                // Spinner View Setup
                String IPLList[] = {
                        "Mumbai Indians",
                        "Kolkata Knight Riders",
                        "Lucknow Super Giants",
                        "Sunrise Hyderabad",
                        "Royal Challengers Bangalore",
                        "Chennai Super Kings"
                };

                ArrayAdapter<String> ipladapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, IPLList);
                spinnerView.setAdapter(ipladapter);

                spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedTeam = IPLList[position];
                        Toast.makeText(getApplicationContext(), "You're a fan of: " + selectedTeam + " Team!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getApplicationContext(), "Seems like you're not a fan of cricket!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        goToElectivePage = findViewById(R.id.go_to_electivePage);

        goToElectivePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Session6Activity3.class);
                startActivity(intent);
            }
        });
    }
}