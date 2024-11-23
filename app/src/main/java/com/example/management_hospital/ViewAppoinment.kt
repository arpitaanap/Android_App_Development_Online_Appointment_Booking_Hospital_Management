package com.example.management_hospital

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewAppointment : AppCompatActivity() {

    private lateinit var recyclerViewAppointments: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var appointmentList: ArrayList<Appointment>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appoinment)

        recyclerViewAppointments = findViewById(R.id.recyclerViewAppointments)
        recyclerViewAppointments.layoutManager = LinearLayoutManager(this)
        appointmentList = ArrayList() // Using ArrayList for better compatibility

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments").child("Dr_ Shrikant Aware")
// Replace with the desired doctor's node
        fetchAppointments()
    }

    private fun fetchAppointments() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                appointmentList.clear()
                for (dataSnapshot in snapshot.children) {
                    val appointment = dataSnapshot.getValue(Appointment::class.java)
                    if (appointment != null) {
                        appointmentList.add(appointment)
                    }
                }
                appointmentAdapter = AppointmentAdapter(appointmentList)
                recyclerViewAppointments.adapter = appointmentAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewAppointment, "Failed to fetch data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
