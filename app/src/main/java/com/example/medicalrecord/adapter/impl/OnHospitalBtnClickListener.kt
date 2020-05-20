package com.example.medicalrecord.adapter.impl

import com.example.medicalrecord.room.model.Hospital

interface OnHospitalBtnClickListener {
    fun onHospitalViewClickListener(hospital: Hospital)
}