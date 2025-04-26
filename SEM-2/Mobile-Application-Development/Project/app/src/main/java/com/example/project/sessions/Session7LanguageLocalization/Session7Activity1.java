package com.example.project.sessions.Session7LanguageLocalization;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.example.project.R;

public class Session7Activity1 extends AppCompatActivity {

    TextView title;
    EditText email, password;
    Button login, change_lang;
    AlertDialog.Builder showLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loadLocale(); // Load the saved locale before setting the content view

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session71);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.title);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        change_lang = findViewById(R.id.change_language);
        showLanguages = new AlertDialog.Builder(this);

        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageList[] = {
                        "English",
                        "हिंदी",
                        "मराठी",
                        "اردو",
                        "ગુજરાતી"
                };

                Map<String, String> languageMap = new HashMap<>();
                languageMap.put("English", "en");
                languageMap.put("हिंदी", "hi");
                languageMap.put("मराठी", "mr");
                languageMap.put("اردو", "ur");
                languageMap.put("ગુજરાતી", "gu");

                showLanguages.setSingleChoiceItems(languageList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedLanguage = languageList[which];
                        String localeCode = languageMap.get(selectedLanguage);

                        if (localeCode != null) {
                            setLocale(localeCode);
                            recreate();
                            Toast.makeText(getApplicationContext(), "You Selected: " + selectedLanguage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                showLanguages.create();
                showLanguages.show();
            }
        });
    }

    private void setLocale(String localeCode) {
        Locale locale = new Locale(localeCode);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);

        getBaseContext().getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("Language", localeCode);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", MODE_PRIVATE);
        String language = sharedPreferences.getString("Language", "en");
        setLocale(language);
    }
}