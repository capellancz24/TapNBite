package com.example.tapnbite_original;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.textfield.TextInputEditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class CreateAccountStudent_Activity extends AppCompatActivity {

    private TextInputEditText firstName, lastName, schoolID, nuEmail, password, confirmPassword;
    private CheckBox agreement;
    private SharedPreferences sharedPreferences;
    private Button signup, btnLogin;
    private boolean isReturningFromPrivacyPolicy = false;

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

        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));

        // Initialize UI components
        firstName = findViewById(R.id.inputFirstName);
        lastName = findViewById(R.id.inputLastName);
        schoolID = findViewById(R.id.inputSchoolID);
        nuEmail = findViewById(R.id.inputNUEmail);
        password = findViewById(R.id.inputPassword);
        confirmPassword = findViewById(R.id.inputConfirmPassword);
        agreement = findViewById(R.id.cbAgreement);
        signup = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        loadUserData();

        // Privacy Policy agreement checkbox click listener
        agreement.setOnClickListener(v -> {
            if (!agreement.isChecked()) {
                return;
            }
            saveUserData();
            Intent intent = new Intent(this, TermsAndConditions_Activity.class);
            startActivity(intent);
        });

        // Signup button click listener
        signup.setOnClickListener(v -> {
            validateAndProceed();
        });

        // Login button click listener (Navigates to Login Page)
        btnLogin.setOnClickListener(v -> {
            clearUserData();
            startActivity(new Intent(this, LoginAccountStudent_Activity.class));
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("fromPrivacyPolicy", false)) {
            isReturningFromPrivacyPolicy = true;
            loadUserData();
        }
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", firstName.getText().toString().trim());
        editor.putString("lastName", lastName.getText().toString().trim());
        editor.putString("schoolID", schoolID.getText().toString().trim());
        editor.putString("nuEmail", nuEmail.getText().toString().trim());
        editor.putString("password", password.getText().toString().trim());
        editor.putString("confirmPassword", confirmPassword.getText().toString().trim());
        editor.putBoolean("PrivacyPolicyAccepted", agreement.isChecked());
        editor.apply();
    }

    private void loadUserData() {
        firstName.setText(sharedPreferences.getString("firstName", ""));
        lastName.setText(sharedPreferences.getString("lastName", ""));
        schoolID.setText(sharedPreferences.getString("schoolID", ""));
        nuEmail.setText(sharedPreferences.getString("nuEmail", ""));
        password.setText(sharedPreferences.getString("password", ""));
        confirmPassword.setText(sharedPreferences.getString("confirmPassword", ""));

        boolean isAccepted = sharedPreferences.getBoolean("PrivacyPolicyAccepted", false);
        agreement.setChecked(isAccepted);
    }

    private void validateAndProceed() {
        if (TextUtils.isEmpty(firstName.getText()) ||
                TextUtils.isEmpty(lastName.getText()) ||
                TextUtils.isEmpty(schoolID.getText()) ||
                TextUtils.isEmpty(nuEmail.getText()) ||
                TextUtils.isEmpty(password.getText()) ||
                TextUtils.isEmpty(confirmPassword.getText())) {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nuEmail.getText().toString().endsWith("@students.nu-dasma.edu.ph")) {
            nuEmail.setError("Must be an NU Dasma student email");
            return;
        }

        Pattern schoolIdPattern = Pattern.compile("^(2021|2022|2023|2024)-\\d{6}$");
        if (!schoolIdPattern.matcher(schoolID.getText().toString()).matches()) {
            schoolID.setError("Invalid School ID format (e.g., 2023-123456)");
            return;
        }

        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Passwords do not match");
            return;
        }

        if (!agreement.isChecked()) {
            Toast.makeText(this, "You must agree to the Terms & Conditions and Privacy Policy", Toast.LENGTH_SHORT).show();
            return;
        }

        // If all validations pass, send data to the server
        sendDataToServer(
                firstName.getText().toString().trim(),
                lastName.getText().toString().trim(),
                schoolID.getText().toString().trim(),
                nuEmail.getText().toString().trim(),
                password.getText().toString().trim()
        );
    }

    private void sendDataToServer(String firstName, String lastName, String schoolID, String nuEmail, String password) {
        new Thread(() -> {
            try {
                // Create the URL object
                URL url = new URL("http://192.168.1.1/tapnbite/signup.php");


                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);


                String postData = "firstName=" + firstName +
                        "&lastName=" + lastName +
                        "&schoolID=" + schoolID +
                        "&nuEmail=" + nuEmail +
                        "&password=" + password;

                // Write the data to the connection
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(postData);
                writer.flush();
                writer.close();
                outputStream.close();

                // Get the response from the server
                int responseCode = connection.getResponseCode();
                InputStream inputStream;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                } else {
                    inputStream = connection.getErrorStream();
                }

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                inputStream.close();

                // Process the response on the main thread
                final String responseData = response.toString();
                runOnUiThread(() -> {
                    if (responseCode == HttpURLConnection.HTTP_OK && responseData.contains("success")) {
                        Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        clearUserData();
                        startActivity(new Intent(this, LoginAccountStudent_Activity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Signup Failed: " + responseData, Toast.LENGTH_SHORT).show();
                    }
                });

                // Disconnect the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(this, "Network Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
    private void clearUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        firstName.setText("");
        lastName.setText("");
        schoolID.setText("");
        nuEmail.setText("");
        password.setText("");
        confirmPassword.setText("");
        agreement.setChecked(false);
    }
}