package com.example.management_hospital;

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FacilitiesActivity : AppCompatActivity() {

    // Declare views
    private lateinit var tvHeader: TextView
    private lateinit var tvEmergencyServices: TextView
    private lateinit var tvMedicalStaff: TextView
    private lateinit var tvLaboratoryFacilities: TextView
    private lateinit var tvRadiologyFacilities: TextView
    private lateinit var tvInpatientFacilities: TextView
    private lateinit var tvOutpatientDepartments: TextView
    private lateinit var tvSpecialtyDepartments: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilities) // The XML layout file

        // Initialize views
        tvHeader = findViewById(R.id.tvHeader)
        tvEmergencyServices = findViewById(R.id.tvEmergencyServices)
        tvMedicalStaff = findViewById(R.id.tvMedicalStaff)
        tvLaboratoryFacilities = findViewById(R.id.tvLaboratoryFacilities)
        tvRadiologyFacilities = findViewById(R.id.tvRadiologyFacilities)
        tvInpatientFacilities = findViewById(R.id.tvInpatientFacilities)
        tvOutpatientDepartments = findViewById(R.id.tvOutpatientDepartments)
        tvSpecialtyDepartments = findViewById(R.id.tvSpecialtyDepartments)

        // Set any text or listeners if required (for example, setting a custom text)
        tvHeader.text = "Our Facilities"

        // Optionally, set more customized content or listeners on the text views
        tvEmergencyServices.setOnClickListener {
            // Handle click for Emergency Services TextView
        }

        tvMedicalStaff.setOnClickListener {
            // Handle click for Medical Staff TextView
        }

        tvLaboratoryFacilities.setOnClickListener {
            // Handle click for Laboratory Facilities TextView
        }

        tvRadiologyFacilities.setOnClickListener {
            // Handle click for Radiology Facilities TextView
        }

        tvInpatientFacilities.setOnClickListener {
            // Handle click for Inpatient Facilities TextView
        }

        tvOutpatientDepartments.setOnClickListener {
            // Handle click for Outpatient Departments TextView
        }

        tvSpecialtyDepartments.setOnClickListener {
            // Handle click for Specialty Departments TextView
        }
    }
}
