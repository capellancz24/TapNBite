package com.example.tapnbite_original;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapnbite_original.SQLiteHelper.DBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CreateAccountStudent_Activity extends AppCompatActivity {
    private CheckBox agreement;
    private Button login, signup;
    private TextInputEditText fullName, schoolID, nuEmail, password, confirmPassword;
    private TextInputLayout txtLayoutFullName, txtLayoutSchoolID, txtLayoutNUEmail, txtLayoutPassword, txtLayoutConfirmPassword;
    private DBHelper DB;

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

        fullName = findViewById(R.id.inputFullName);
        schoolID = findViewById(R.id.inputNUID);
        nuEmail = findViewById(R.id.inputNUEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);

        txtLayoutFullName = findViewById(R.id.txtLayoutFullName);
        txtLayoutSchoolID = findViewById(R.id.txtLayoutNUID);
        txtLayoutNUEmail = findViewById(R.id.txtLayoutEmail);
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword);
        txtLayoutConfirmPassword = findViewById(R.id.txtLayoutConfirmPassword);

        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnSignUp);

        agreement = findViewById(R.id.cbAgreement);
        
        fullNameTextWatcher();
        schoolIDTextWatcher();
        schoolEmailTextWatcher();
        passwordTextWatcher();

        btnSignUpClicked();
        btnLoginClicked();
    }

    private void btnSignUpClicked(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFullName() && !validateSchoolID() && !validateSchoolEmail() && !validatePassword()) {
                    return;
                }

                if (!validateFullName() || !validateSchoolID() || !validateSchoolEmail() || !validatePassword()) {
                    return;
                }

                if (!agreement.isChecked()) {
                    Toast.makeText(CreateAccountStudent_Activity.this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                DB = new DBHelper(CreateAccountStudent_Activity.this);

                // If all validations pass, proceed with account creation
                String fullNameValue = fullName.getText().toString().trim();
                String schoolIDValue = schoolID.getText().toString().trim();
                String nuEmailValue = nuEmail.getText().toString().trim();
                String passwordValue = password.getText().toString().trim();
                String confirmPasswordValue = confirmPassword.getText().toString().trim();

                if (passwordValue.equals(confirmPasswordValue)){
                    Boolean checkEmail = DB.checkEmail(nuEmailValue);
                    if (!checkEmail){
                        Boolean insert = DB.addCustomerData(fullNameValue, schoolIDValue, nuEmailValue, passwordValue);
                        if (insert){
                            Toast.makeText(CreateAccountStudent_Activity.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateAccountStudent_Activity.this, LoginAccountStudent_Activity.class);
                        }
                    }
                } else {
                    Toast.makeText(CreateAccountStudent_Activity.this, "Password do not match.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void btnLoginClicked(){
        login.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccountStudent_Activity.this, LoginAccountStudent_Activity.class);
            startActivity(intent);
        });
    }

    private void fullNameTextWatcher(){
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtLayoutFullName.setError(null);
                txtLayoutFullName.setErrorEnabled(false);
            }
        });
    }

    private void schoolIDTextWatcher(){
        schoolID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtLayoutSchoolID.setError(null);
                txtLayoutSchoolID.setErrorEnabled(false);
            }
        });
    }

    private void schoolEmailTextWatcher(){
        nuEmail.addTextChangedListener(new TextWatcher() {
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
    }

    private void passwordTextWatcher(){
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

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                    txtLayoutConfirmPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                } else {
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_NONE);
                    txtLayoutConfirmPassword.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }
            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtLayoutConfirmPassword.setError(null);
                txtLayoutConfirmPassword.setErrorEnabled(false);
            }
        });

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    txtLayoutConfirmPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                } else {
                    txtLayoutPassword.setEndIconMode(TextInputLayout.END_ICON_NONE);
                    txtLayoutConfirmPassword.setEndIconMode(TextInputLayout.END_ICON_NONE);
                }
            }
        });
    }

    private Boolean validateFullName() {
        String val = fullName.getText().toString().trim();

        if (val.isEmpty()) {
            txtLayoutFullName.setError("This field is required.");
            return false;
        }

        String[] nameParts = val.split("\\s+"); // Split by one or more whitespace characters

        if (nameParts.length < 2) {
            txtLayoutFullName.setError("Please enter both first and last name.");
            return false;
        }

        // Check for valid characters (letters, spaces, hyphens, apostrophes)
        String namePattern = "^[a-zA-Z\\s'-]+$"; // Allows letters, spaces, hyphens, and apostrophes
        Pattern pattern = Pattern.compile(namePattern);
        Matcher matcher = pattern.matcher(val);

        if (!matcher.matches()) {
            txtLayoutFullName.setError("Invalid name format.");
            return false;
        }

        // Check for minimum and maximum length (optional)
        if (val.length() < 2 || val.length() > 25) {
            txtLayoutFullName.setError("Name must be between 10 and 25 characters long.");
            return false;
        }

        return true;
    }

    private Boolean validateSchoolID() {
        String val = schoolID.getText().toString().trim();

        if (val.isEmpty()) {
            txtLayoutSchoolID.setError("This field is required.");
            return false;
        }

        if (val.length() != 11) {
            txtLayoutSchoolID.setError("Must be 11 characters long. (e.g. 2025-172077)");
            return false;
        }

        String regex = "^(\\d{4})-(\\d{6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);

        if (!matcher.matches()) {
            txtLayoutSchoolID.setError("Invalid format. Use YYYY-ID (e.g. 2025-172077)");
            return false;
        }

        return true;
    }

    private Boolean validateSchoolEmail(){
        String val = nuEmail.getText().toString().trim();

        String emailPattern = "^[a-zA-Z]+[a-zA-Z]*[0-9]*@(nu-dasma\\.edu\\.ph|students\\.nu-dasma\\.edu\\.ph)$";

        if (val.isEmpty()) {
            txtLayoutNUEmail.setError("This field is required.");
            return false;
        }

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(val);

        if (!matcher.matches()) {
            txtLayoutNUEmail.setError("Invalid NU Email format.");
            return false;
        }

        return true;
    }

    private Boolean validatePassword() {
        String val = password.getText().toString().trim();
        String val2 = confirmPassword.getText().toString().trim();

        if (val.isEmpty() && val2.isEmpty()) {
            txtLayoutPassword.setError("This field is required.");
            txtLayoutConfirmPassword.setError("This field is required.");
            return false;
        }

        if (val.isEmpty() || val2.isEmpty()) {
            txtLayoutPassword.setError("This field is required.");
            txtLayoutConfirmPassword.setError("This field is required.");
            return false;
        }

        if (val.length() < 8) {
            txtLayoutPassword.setError("Password must be at least 8 characters long.");
            return false;
        }

        if (!isPasswordStrong(val)) {
            txtLayoutPassword.setError("Password must contain exactly one special character.");
            return false;
        }

        if (!val.equals(val2)) {
            txtLayoutConfirmPassword.setError("Passwords do not match.");
            return false;
        }

        return true;
    }

    private boolean isPasswordStrong(String password) {
        int specialCharCount = 0;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                specialCharCount++;
            }
        }

        return specialCharCount == 1;
    }
}