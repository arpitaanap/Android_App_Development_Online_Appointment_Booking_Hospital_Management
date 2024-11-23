package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_doc)

        // Initialize the book appointment buttons
        val bookAppointmentButton1: Button = findViewById(R.id.bookAppointmentButton1)
        val bookAppointmentButton2: Button = findViewById(R.id.bookAppointmentButton2)
        val bookAppointmentButton3: Button = findViewById(R.id.bookAppointmentButton3)
        val bookAppointmentButton4: Button = findViewById(R.id.bookAppointmentButton4)
        val bookAppointmentButton5: Button = findViewById(R.id.bookAppointmentButton5)

        // Set click listeners for each button
        bookAppointmentButton1.setOnClickListener {
            navigateToAppointmentActivity()
        }
        bookAppointmentButton2.setOnClickListener {
            navigateToAppointmentActivity()
        }
        bookAppointmentButton3.setOnClickListener {
            navigateToAppointmentActivity()
        }
        bookAppointmentButton4.setOnClickListener {
            navigateToAppointmentActivity()
        }
        bookAppointmentButton5.setOnClickListener {
            navigateToAppointmentActivity()
        }
    }

    // Function to navigate to AppointmentActivity
    private fun navigateToAppointmentActivity() {
        val intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)
    }
}
