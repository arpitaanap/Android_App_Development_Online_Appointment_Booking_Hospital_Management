package com.example.management_hospital

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AppointmentActivity : AppCompatActivity() {

    private lateinit var etDate: EditText
    private lateinit var etMobile: EditText
    private lateinit var etAge: EditText
    private lateinit var etPatientName: EditText
    private lateinit var rgTimeSlot: RadioGroup
    private lateinit var rgGender: RadioGroup
    private lateinit var spinnerDoctor: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        // Initialize views
        etDate = findViewById(R.id.etDate)
        etMobile = findViewById(R.id.etMobileNumber)
        etAge = findViewById(R.id.etAge)
        etPatientName = findViewById(R.id.etPatientName)
        rgTimeSlot = findViewById(R.id.rgTimeSlot)
        rgGender = findViewById(R.id.rgGender)
        spinnerDoctor = findViewById(R.id.spinnerDoctor)

        // Configure age field to accept only numeric input and set a max length of 3 digits
        etAge.inputType = InputType.TYPE_CLASS_NUMBER
        etAge.filters = arrayOf(InputFilter.LengthFilter(3))

        // Populate the spinner with doctor names
        val doctors = arrayOf(
            "Select Doctor",
            "Dr. Shrikant Aware",
            "Dr. Manisha Aware",
            "Dr. Sandip Agwan",
            "Dr. Kunal Mali",
            "Dr. Ruchita Shinde"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, doctors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDoctor.adapter = adapter

        // Set OnClickListener for Date EditText
        etDate.setOnClickListener { showDatePickerDialog() }

        // Button to confirm and display the selected data
        val btnBookAppointment: Button = findViewById(R.id.btnBookAppointment)
        btnBookAppointment.setOnClickListener {
            // Get inputs
            val patientName = etPatientName.text.toString().trim()
            val age = etAge.text.toString().trim()
            val mobileNumber = etMobile.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.rbMale -> "Male"
                R.id.rbFemale -> "Female"
                else -> ""
            }

            // Get the selected time slot
            val selectedTimeId = rgTimeSlot.checkedRadioButtonId
            val timeSlot = when (selectedTimeId) {
                R.id.rbMorning -> "Morning: 9:00 AM - 12:00 PM"
                R.id.rbAfternoon -> "Afternoon: 1:00 PM - 3:00 PM"
                R.id.rbEvening -> "Evening: 5:00 PM - 9:00 PM"
                else -> ""
            }

            // Get the selected date
            val date = etDate.text.toString().trim()

            // Get the selected doctor
            val selectedDoctor = spinnerDoctor.selectedItem.toString()

            // Validate inputs
            when {
                patientName.isEmpty() -> Toast.makeText(this, "Please enter the patient's name", Toast.LENGTH_SHORT).show()
                age.isEmpty() || !age.matches("\\d+".toRegex()) || age.toInt() == 0 || age.toInt() > 999 ->
                    Toast.makeText(this, "Please enter a valid age (1-999)", Toast.LENGTH_SHORT).show()
                mobileNumber.isEmpty() || mobileNumber.length != 10 || !mobileNumber.matches("\\d{10}".toRegex()) ->
                    Toast.makeText(this, "Please enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show()
                gender.isEmpty() -> Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
                date.isEmpty() -> Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                timeSlot.isEmpty() -> Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show()
                selectedDoctor == "Select Doctor" -> Toast.makeText(this, "Please select a doctor", Toast.LENGTH_SHORT).show()
                else -> {
                    // Save the appointment to Firebase
                    saveAppointment(patientName, age, mobileNumber, gender, selectedDoctor, date, timeSlot)
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create and display the DatePickerDialog
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            etDate.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun saveAppointment(
        patientName: String,
        age: String,
        mobileNumber: String,
        gender: String,
        selectedDoctor: String,
        date: String,
        timeSlot: String
    ) {
        // Replace invalid characters in the doctor's name
        val sanitizedDoctorName = selectedDoctor.replace(".", "_")

        // Get a reference to the Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val appointmentsRef = database.getReference("appointments")

        // Create a new appointment under the sanitized doctor name
        val doctorRef = appointmentsRef.child(sanitizedDoctorName)
        val newAppointment = mapOf(
            "patientName" to patientName,
            "age" to age,
            "mobileNumber" to mobileNumber,
            "gender" to gender,
            "date" to date,
            "timeSlot" to timeSlot
        )

        doctorRef.push().setValue(newAppointment)
            .addOnSuccessListener {
                // Show toast message for successful appointment booking
                Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()

                // Redirect to DoctorProfileActivity after successful booking
                val intent = Intent(this, DoctorProfileActivity::class.java)
                // Optionally, you can pass the selected doctor's name if needed
                intent.putExtra("doctorName", selectedDoctor)
                startActivity(intent)
                finish()  // Finish the current activity to prevent going back
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to book appointment: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
