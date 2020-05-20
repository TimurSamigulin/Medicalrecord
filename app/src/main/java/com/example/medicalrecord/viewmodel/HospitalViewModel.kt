package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.dao.DAOHospital
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Hospital
import com.example.medicalrecord.room.repository.HospitalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HospitalViewModel(application: Application): AndroidViewModel(application) {
    private val repository: HospitalRepository
    val allHospital: LiveData<List<Hospital>>

    init {
        val daoHospital: DAOHospital = DiseaseDatabase.getInstance(application, viewModelScope).daoHospital()
        repository = HospitalRepository(daoHospital)

        allHospital = repository.allHospitals
    }

    fun insertHospital(hospital: Hospital) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHospital(hospital)
        }
    }

    fun insertHospitals(hospitals: List<Hospital>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHospitals(hospitals)
        }
    }

    fun updateHospital(hospital: Hospital) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHospital(hospital)
        }
    }
}