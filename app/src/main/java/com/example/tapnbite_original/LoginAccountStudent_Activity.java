package com.example.tapnbite_original;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

        /*-------------------------------------- TextView ---------------------------------------*/

        TextView forgotPassword = findViewById(R.id.tvForgotPassword);

        /*--------------------------------- TextInputEditTexts ----------------------------------*/

        TextInputEditText email = findViewById(R.id.inputEmail);
        TextInputEditText password = findViewById(R.id.inputPassword);
        TextInputLayout passLayout = findViewById(R.id.tilPassword);

        /*--------------------------------- Login Button Click Listener ----------------------------------*/
        login.setOnClickListener(v -> {
            String emailInput = email.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();

            if (TextUtils.isEmpty(emailInput)) {
                email.setError("Email is required");
                email.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(passwordInput)) {
                password.setError("Password is required");
                password.requestFocus();
                return;
            }

            // Proceed with login process
            loginUser(emailInput, passwordInput);
        });
    }

    private void loginUser(String nuEmail, String password) {
        new Thread(() -> {
            try {
                // Create the URL object
                URL url = new URL("http://10.0.2.2/tapnbite/login.php"); // Replace with your server URL

                System.out.println("URL: " + url.toString());
                System.out.println("Request Body: nuEmail=" + nuEmail + "&password=" + password);

                // Open a connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true); // Enable writing to the connection
                connection.setDoInput(true); // Enable reading from the connection

                // Create the request body
                String postData = "nuEmail=" + nuEmail + "&password=" + password;

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

                // Log the server response
                System.out.println("Server Response: " + response.toString());

                // Process the response on the main thread
                final String responseData = response.toString();
                runOnUiThread(() -> {
                    try {
                        // Parse the JSON response
                        JSONObject jsonResponse = new JSONObject(responseData);
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");

                        if (status.equals("success")) {
                            // Login successful
                            Toast.makeText(LoginAccountStudent_Activity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginAccountStudent_Activity.this, Onboarding1_Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Login failed
                            Toast.makeText(LoginAccountStudent_Activity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Log the error and the raw server response
                        System.out.println("Error parsing server response: " + e.getMessage());
                        System.out.println("Raw Server Response: " + responseData);
                        Toast.makeText(LoginAccountStudent_Activity.this, "Error parsing server response", Toast.LENGTH_SHORT).show();
                    }
                });

                // Disconnect the connection
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(LoginAccountStudent_Activity.this, "Network Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}