package com.example.medicalrecord.adapter.impl

import com.example.medicalrecord.room.model.Doctor

interface OnDoctorBtnClickListener {
    fun onDoctorViewClickListener(doctor: Doctor)
}