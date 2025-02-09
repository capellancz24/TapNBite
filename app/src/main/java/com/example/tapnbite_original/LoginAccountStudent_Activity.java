package com.example.tapnbite_original;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapnbite_original.SQLiteHelper.DBHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginAccountStudent_Activity extends AppCompatActivity {

    private Button login, signup;
    private TextView forgotPassword;
    private TextInputEditText email, password;
    private TextInputLayout txtLayoutNUEmail, txtLayoutPassword;
    private DBHelper DB;


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

        signup = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.btnLogin);

        email = findViewById(R.id.inputNUEmail);
        password = findViewById(R.id.inputPassword);
        forgotPassword = findViewById(R.id.tvForgotPassword);

        txtLayoutNUEmail = findViewById(R.id.txtLayoutEmail);
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword);

        emailAndPasswordTextWatcher();

        btnLoginClicked();
        btnSignUpClicked();
    }

    private void btnLoginClicked(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmailAndPassword()) {
                    return;
                }

                DB = new DBHelper(LoginAccountStudent_Activity.this);

                String emailValue = email.getText().toString().trim();
                String passwordValue = password.getText().toString().trim();

                if (DB.checkEmailAndPassword(emailValue, passwordValue)) {
                    Toast.makeText(LoginAccountStudent_Activity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAccountStudent_Activity.this, Menu_Activity.class);
                    startActivity(intent);
                    finish(); // Optionally finish the login activity
                } else {
                    Toast.makeText(LoginAccountStudent_Activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void btnSignUpClicked (){
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAccountStudent_Activity.this, CreateAccountStudent_Activity.class);
            startActivity(intent);
        });
    }

    private void emailAndPasswordTextWatcher(){
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtLayoutNUEmail.setError(null);
                txtLayoutNUEmail.setErrorEnabled(false);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                } else {
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtLayoutPassword.setError(null);
                txtLayoutPassword.setErrorEnabled(false);
            }
        });
    }

    private Boolean validateEmailAndPassword() {
        String val = password.getText().toString().trim();
        String val2 = email.getText().toString().trim();


        if (val.isEmpty()) {
            txtLayoutPassword.setError("Please enter your password.");
            return false;
        }

        if (val2.isEmpty()) {
            txtLayoutNUEmail.setError("Please enter your email.");
            return false;
        }

        return true;
    }


}