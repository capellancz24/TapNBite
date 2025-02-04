package com.example.tapnbite_original;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateAccountStudent_Activity extends AppCompatActivity {
    private String password;
    private Spinner accountType;
    private Button login, signup;
    private TextInputEditText lastName, schoolID, nuEmail, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        getWindow().setNavigationBarColor(ContextCompat.getColor(CreateAccountStudent_Activity.this, R.color.primary));

        /*--------------------------------------- Spinner ---------------------------------------*/

        accountType = findViewById(R.id.ddAccountType);

        /*--------------------------------------- Spinner ---------------------------------------*/


        /*--------------------------------------- Buttons ---------------------------------------*/

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccountStudent_Activity.this, LoginAccountStudent_Activity.class);
            startActivity(intent);
        });

        signup = findViewById(R.id.btnSignUp);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccountStudent_Activity.this, TermsAndConditions_Activity.class);
            startActivity(intent);
        });


        /*--------------------------------------- Buttons ---------------------------------------*/


        /*--------------------------------- TextInputEditTexts ----------------------------------*/


        TextInputEditText firstName = findViewById(R.id.inputFirstName);
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.length() >= 8) {
                    Pattern pattern = Pattern.compile("[a-zA-Z]");
                    Matcher matcher = pattern.matcher(password);

                    boolean isPassContainsSpeChar = matcher.find();
                    if (isPassContainsSpeChar){

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastName = findViewById(R.id.inputLastName);
        schoolID = findViewById(R.id.inputSchoolID);
        nuEmail = findViewById(R.id.inputNUEmail);
        //password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);

        /*--------------------------------- TextInputEditTexts ----------------------------------*/


        /*-------------------------------------- Checkbox ---------------------------------------*/

        CheckBox agreement = findViewById(R.id.cbAgreement);

        /*-------------------------------------- Checkbox ---------------------------------------*/

    }

}