package com.example.commonthread.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.commonthread.R;
import com.example.commonthread.viewmodel.LoginViewModel;

/*
 * LoginActivity is responsible for displaying the UI and handling user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    // ViewModel instance
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout for login screen
        setContentView(R.layout.activity_login);

        // Get references to UI components
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvResult = findViewById(R.id.tvResult);

        // Initialize ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Handle login button click
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Send user input to ViewModel
            loginViewModel.login(email, password);
        });

        // Observe login result from ViewModel
        loginViewModel.getLoginResult().observe(this, result -> {
            tvResult.setText(result);

            // Change color based on success or failure
            if (result.equals("Login successful. Welcome to CommonThread.")) {
                tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });
    }
}