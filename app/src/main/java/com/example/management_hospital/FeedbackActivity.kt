package com.example.management_hospital; // Replace with your package name

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var feedbackField: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback) // Replace with your layout file name

        // Initialize the views
        nameField = findViewById(R.id.nameField)
        emailField = findViewById(R.id.emailField)
        feedbackField = findViewById(R.id.feedbackField)
        submitButton = findViewById(R.id.submitButton)

        // Set onClickListener for the submit button
        submitButton.setOnClickListener { validateInputs() }
    }

    private fun validateInputs() {
        // Get the text entered by the user
        val name = nameField.text.toString().trim()
        val email = emailField.text.toString().trim()
        val feedback = feedbackField.text.toString().trim()

        // Check if any field is empty
        if (name.isEmpty()) {
            nameField.error = "Name is required"
            return
        }

        if (email.isEmpty()) {
            emailField.error = "Email is required"
            return
        }

        // Validate email format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.error = "Please enter a valid email address"
            return
        }

        if (feedback.isEmpty()) {
            feedbackField.error = "Feedback is required"
            return
        }

        // If all fields are valid, show a success message
        Toast.makeText(this, "Feedback Submitted Successfully!", Toast.LENGTH_SHORT).show()

        // Optionally, clear the fields after submission
        nameField.text.clear()
        emailField.text.clear()
        feedbackField.text.clear()
    }
}
