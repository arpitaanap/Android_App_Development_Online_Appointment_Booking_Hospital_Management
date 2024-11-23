package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class docLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_login)

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find views
        val doctorIdField = findViewById<EditText>(R.id.docid)
        val submitButton = findViewById<Button>(R.id.submit_btn)

        // Set up button click listener
        submitButton.setOnClickListener {
            val enteredId = doctorIdField.text.toString().trim()

            when (enteredId) {
                "001122" -> {
                    // Navigate to Dr. Shrikant Aware's activity
                    val intent = Intent(this, dr_shrikant::class.java)
                    startActivity(intent)
                }
                "002233" -> {
                    // Navigate to Dr. Manisha Aware's activity
                    val intent = Intent(this, doctor::class.java)
                    startActivity(intent)
                }
                "003344" -> {
                    // Navigate to Dr. Sandip Agwan's activity
                    val intent = Intent(this, Sandip_Agwan::class.java)
                    startActivity(intent)
                }
                "004455" -> {
                    // Navigate to Dr. Kunal Mali's activity
                    val intent = Intent(this, kunal_mali::class.java)
                    startActivity(intent)
                }
                "005566" -> {
                    // Navigate to Dr. Ruchita Shinde's activity
                    val intent = Intent(this, Ruchita_Shinde::class.java)
                    startActivity(intent)
                }
                else -> {
                    // Show error message for invalid ID
                    Toast.makeText(this, "Invalid ID. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }

            // Optionally clear the input field
            doctorIdField.text.clear()
        }
    }
}
