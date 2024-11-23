package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ruchita_Shinde : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ruchita_shinde)

        // Adjusting for system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Button click listener to navigate to ViewAppointment
        val viewAppointmentButton = findViewById<Button>(R.id.view_appointment_button)
        viewAppointmentButton.setOnClickListener {
            // Create an Intent to navigate to ViewAppointment activity
            val intent = Intent(this, doc6::class.java)
            startActivity(intent)
        }
    }
}
