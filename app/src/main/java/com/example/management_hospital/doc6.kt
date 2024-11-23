package com.example.management_hospital

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class doc6 : AppCompatActivity() {

    private lateinit var recyclerViewAppointments: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var appointmentList: ArrayList<Appointment>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc6)

        recyclerViewAppointments = findViewById(R.id.recyclerViewAppointments)
        recyclerViewAppointments.layoutManager = LinearLayoutManager(this)
        appointmentList = ArrayList()

        // Firebase Database reference for Dr. 2
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments").child("Dr_ Ruchita Shinde")
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
                Toast.makeText(this@doc6, "Failed to fetch data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
