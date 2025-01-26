package com.example.tapnbite_original;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;


public class LoginAccountStudent_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_account_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setNavigationBarColor(ContextCompat.getColor(LoginAccountStudent_Activity.this, R.color.primary));

        /*--------------------------------------- Buttons ---------------------------------------*/

        Button signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAccountStudent_Activity.this, CreateAccountStudent_Activity.class);
            startActivity(intent);
        });

        Button login = findViewById(R.id.btnLogin);

        /*--------------------------------------- Buttons ---------------------------------------*/


        /*-------------------------------------- TextView ---------------------------------------*/

        TextView forgotPassword = findViewById(R.id.tvForgotPassword);

        /*-------------------------------------- TextView ---------------------------------------*/


        /*--------------------------------- TextInputEditTexts ----------------------------------*/

        TextInputEditText email = findViewById(R.id.inputEmail);
        TextInputEditText password = findViewById(R.id.inputPassword);

        /*--------------------------------- TextInputEditTexts ----------------------------------*/

    }
}