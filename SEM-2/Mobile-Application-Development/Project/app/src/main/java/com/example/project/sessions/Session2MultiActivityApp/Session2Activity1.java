package com.example.project.Session2MultiActivityApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;

public class Session2Activity1 extends AppCompatActivity {

    TextView output;
    Button clickView, nextButton;
    EditText operation, num1, num2;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session21);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        output = findViewById(R.id.output);
        clickView = findViewById(R.id.button);
        operation = findViewById(R.id.operation);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        nextButton = findViewById(R.id.button_next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Session2Activity1.this, Session2Activity2.class);
                intent.putExtra("userId", 54);
                intent.putExtra("email", "m.varaliya@somaiya.edu");
                startActivity(intent);
                finish();
            }
        });
    }

    public void show(View view) {
        try {
            int number1 = Integer.parseInt(num1.getText().toString());
            int number2 = Integer.parseInt(num2.getText().toString());
            String operator = operation.getText().toString().trim();

            switch (operator) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot divide by zero!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Invalid operation!", Toast.LENGTH_SHORT).show();
                    return;
            }

            output.setText("Result: " + result);

            Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Please enter valid numbers!", Toast.LENGTH_SHORT).show();
        }
    }
}