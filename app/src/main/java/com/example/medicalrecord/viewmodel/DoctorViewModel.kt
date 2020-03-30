package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Doctor
import com.example.medicalrecord.room.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoctorViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DoctorRepository
    val currentDoctor: LiveData<List<Doctor>>
    val oldDoctor: LiveData<List<Doctor>>

    init {
        val daoDoctor = DiseaseDatabase.getInstance(application, viewModelScope).daoDoctor()
        repository = DoctorRepository(daoDoctor)

        currentDoctor = repository.currentDoctor
        oldDoctor = repository.oldDoctor
    }

    fun insetDoctor(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDoctor(doctor)
        }
    }

    fun updateDoctor(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDoctor(doctor)
        }
    }

    fun deleteDoctor(doctor: Doctor) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDoctor(doctor)
        }
    }
}