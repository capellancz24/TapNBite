package com.example.tapnbite_original;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginAccountStudent_Activity extends AppCompatActivity {

    private Button login, signup;
    private TextView forgotPassword;
    private TextInputEditText email, password;
    private TextInputLayout txtInLayoutEmail, txtInLayoutPassword;

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
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        forgotPassword = findViewById(R.id.tvForgotPassword);
        txtInLayoutEmail = findViewById(R.id.tilEmail);
        txtInLayoutPassword = findViewById(R.id.tilPassword);

        loginClicked();
        signupClicked();
    }

    private void loginClicked (){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString().trim();
                String passInput = password.getText().toString().trim();

                //checks is email is empty
                if (emailInput.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();

                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();

                    txtInLayoutEmail.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                    txtInLayoutEmail.setError("Email should not be empty.");
                } else {
                    //Here you can write the codes for checking email
                    txtInLayoutEmail.setError(null); // Clear the error
                    txtInLayoutEmail.setBoxStrokeColor(Color.WHITE); // Reset to
                }

                //checks if password is empty
                if (passInput.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();

                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();

                    txtInLayoutPassword.setError("Password should not be empty");
                } else {
                    //Here you can write the codes for checking password
                }

            }
        });
    }
    private void signupClicked (){
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAccountStudent_Activity.this, CreateAccountStudent_Activity.class);
            startActivity(intent);
        });
    }

}