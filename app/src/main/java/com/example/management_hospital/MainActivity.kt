package com.example.management_hospital;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare views
    private lateinit var buttonDoctorLogin: Button
    private lateinit var PatientBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        buttonDoctorLogin = findViewById(R.id.doctor_login_btn)
        PatientBtn = findViewById(R.id.patient_login_btn)


        // Set click listener for Sign Up button


        // Set click listener for Log In button
        PatientBtn.setOnClickListener {
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
