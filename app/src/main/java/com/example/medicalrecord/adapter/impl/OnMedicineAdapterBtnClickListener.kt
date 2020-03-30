package com.example.medicalrecord.adapter.impl

import com.example.medicalrecord.room.model.Medicine

interface OnMedicineAdapterBtnClickListener {
    fun onMedicineViewClickListener(medicine: Medicine)
}