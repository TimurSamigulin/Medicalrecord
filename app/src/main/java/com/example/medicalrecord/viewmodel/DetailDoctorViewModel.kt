package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Doctor
import com.example.medicalrecord.room.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailDoctorViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DoctorRepository

    init {
        val daoDoctor = DiseaseDatabase.getInstance(application, viewModelScope).daoDoctor()
        repository = DoctorRepository(daoDoctor)
    }

    fun updateDoctor(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDoctor(doctor)
        }
    }
}