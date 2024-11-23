package com.example.management_hospital

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class doc3 : AppCompatActivity() {

    private lateinit var recyclerViewAppointments: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var appointmentList: ArrayList<Appointment>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc3)

        recyclerViewAppointments = findViewById(R.id.recyclerViewAppointments)
        recyclerViewAppointments.layoutManager = LinearLayoutManager(this)
        appointmentList = ArrayList()

        // Firebase Database reference for Dr. 3
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments").child("Dr_ Manisha Aware")
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
                Toast.makeText(this@doc3, "Failed to fetch data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
