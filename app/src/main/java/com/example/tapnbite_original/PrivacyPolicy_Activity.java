package com.example.tapnbite_original;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicy_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(v -> {
            // Save agreement status in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("PrivacyPolicyAccepted", true);
            editor.apply();

            Intent intent = new Intent(this, CreateAccountStudent_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}
