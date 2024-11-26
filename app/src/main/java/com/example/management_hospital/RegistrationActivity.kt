package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize Firebase instances
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val editTextUsername: EditText = findViewById(R.id.editTextUsername)
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val editTextConfirmPassword: EditText = findViewById(R.id.editTextConfirmPassword)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editTextConfirmPassword.text.toString().trim()

            // Input validation
            if (TextUtils.isEmpty(username)) {
                editTextUsername.error = "Username is required"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(email)) {
                editTextEmail.error = "Email is required"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                editTextPassword.error = "Password is required"
                return@setOnClickListener
            }
            if (password.length < 6) {
                editTextPassword.error = "Password must be at least 6 characters long"
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                editTextConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Register user with Firebase Authentication
            registerUser(username, email, password)
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User registration successful
                    val userId = auth.currentUser?.uid

                    // Save the username in Firebase Realtime Database
                    if (userId != null) {
                        database.child("users").child(userId).child("username").setValue(username)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "Registration successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Navigate to LoginActivity after successful registration
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish() // Close the current registration activity
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Failed to save user details: ${dbTask.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                } else {
                    // Registration failed
                    Toast.makeText(
                        this,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}
