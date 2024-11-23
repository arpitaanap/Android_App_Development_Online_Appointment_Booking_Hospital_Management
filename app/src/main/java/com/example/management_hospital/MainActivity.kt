package com.example.management_hospital;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare views
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button
    private lateinit var buttonDoctorLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        btnSignUp = findViewById(R.id.buttonSignUp)
        btnLogin = findViewById(R.id.buttonLogin)
        buttonDoctorLogin = findViewById(R.id.buttonDoctorLogin)

        // Set click listener for Sign Up button
        btnSignUp.setOnClickListener {
            // Navigate to Registration Activity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Log In button
        btnLogin.setOnClickListener {
            // Navigate to Login Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Skip button
        buttonDoctorLogin.setOnClickListener {
            // Navigate to Dashboard or Home Activity (Example: DoctorProfileActivity)
            val intent = Intent(this, docLogin::class.java)
            startActivity(intent)
        }
    }
}
