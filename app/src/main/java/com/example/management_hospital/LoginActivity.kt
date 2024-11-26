package com.example.management_hospital

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var noAccountText: TextView // Reference to the "Don't have an account?" TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Initialize views
        email = findViewById(R.id.username) // Replace username field with email
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.submit_btn)
        noAccountText = findViewById(R.id.no_account_text) // Find the TextView

        // Set login button click listener
        loginButton.setOnClickListener {
            val emailInput = email.text.toString().trim()
            val passwordInput = password.text.toString().trim()

            // Validate inputs
            if (validateInputs(emailInput, passwordInput)) {
                loginUser(emailInput, passwordInput)
            }
        }

        // Set "Don't have an account?" TextView click listener
        noAccountText.setOnClickListener {
            // Navigate to Registration Activity
            val intent = Intent(this, RegistrationActivity::class.java) // Replace with your RegistrationActivity class
            startActivity(intent)
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            this.email.error = "Email cannot be empty"
            this.email.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(password)) {
            this.password.error = "Password cannot be empty"
            this.password.requestFocus()
            return false
        }
        if (password.length < 6) {
            this.password.error = "Password must be at least 6 characters"
            this.password.requestFocus()
            return false
        }
        return true
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Fetch user details from Realtime Database
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        database.child("users").child(userId).child("username")
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val username = snapshot.value as? String
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Welcome, $username!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Navigate to the next screen
                                    val intent = Intent(
                                        this@LoginActivity,
                                        DoctorProfileActivity::class.java // Replace with your activity
                                    )
                                    startActivity(intent)
                                    finish()
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Failed to fetch user details: ${error.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Login failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}
