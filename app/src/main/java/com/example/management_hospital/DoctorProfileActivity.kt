package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DoctorProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile) // Use your provided XML layout

        // Initialize the CardViews by their IDs
        val cardProfile: CardView = findViewById(R.id.cardProfile)
        val cardSettings: CardView = findViewById(R.id.cardSettings)
        val cardFacilities: CardView = findViewById(R.id.cardFacilities)
        val cardFeedback: CardView = findViewById(R.id.cardFeedback)

        // Set onClickListeners for each CardView to handle navigation

        // Doctor Profile Card Click Listener
        cardProfile.setOnClickListener {
            // Redirect to Doctor Profile page
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        // Appointment Booking Card Click Listener
        cardSettings.setOnClickListener {
            // Redirect to Appointment Booking page
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }

        // Facilities Card Click Listener
        cardFacilities.setOnClickListener {
            // Redirect to Facilities page
            val intent = Intent(this, FacilitiesActivity::class.java)
            startActivity(intent)
        }

        // Feedback Card Click Listener
        cardFeedback.setOnClickListener {
            // Redirect to Feedback page
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }

        // Optional: Add more card listeners as needed
        // Example for Gallery Card
        /*
        cardGallery.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
        */
    }
}
