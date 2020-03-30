package com.example.medicalrecord.adapter.impl

import com.example.medicalrecord.room.model.Disease

interface OnDiseaseAdapterBtnClickListener {
    fun onDiseaseViewClickListener(disease: Disease)
}