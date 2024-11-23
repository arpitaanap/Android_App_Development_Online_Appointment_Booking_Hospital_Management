package com.example.management_hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppointmentAdapter(private val appointmentList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.bind(appointment)
    }

    override fun getItemCount(): Int = appointmentList.size

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPatientName: TextView = itemView.findViewById(R.id.tvPatientName)
        private val tvAppointmentDetails: TextView = itemView.findViewById(R.id.tvAppointmentDetails)
        private val tvTimeSlot: TextView = itemView.findViewById(R.id.tvTimeSlot)

        fun bind(appointment: Appointment) {
            tvPatientName.text = appointment.patientName
            tvAppointmentDetails.text = "Age: ${appointment.age}, Gender: ${appointment.gender}, Date: ${appointment.date}"
            tvTimeSlot.text = "Time Slot: ${appointment.timeSlot}"
        }
    }
}
