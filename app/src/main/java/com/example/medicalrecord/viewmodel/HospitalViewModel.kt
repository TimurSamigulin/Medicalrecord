package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.medicalrecord.room.repository.HospitalRepository

class HospitalViewModel(application: Application): AndroidViewModel(application) {
    private val repository: HospitalRepository
    init {
        repository = HospitalRepository()
    }



}