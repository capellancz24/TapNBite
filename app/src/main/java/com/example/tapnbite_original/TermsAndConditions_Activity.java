package com.example.tapnbite_original;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TermsAndConditions_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terms_and_conditions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(TermsAndConditions_Activity.this, R.color.end_color));

        Button next = findViewById(R.id.btnNext);
        next.setOnClickListener(v -> {
            Intent intent = new Intent(TermsAndConditions_Activity.this, PrivacyPolicy_Activity.class);
            startActivity(intent);
        });

        ImageButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(TermsAndConditions_Activity.this, CreateAccountStudent_Activity.class);
            startActivity(intent);
        });
    }
}