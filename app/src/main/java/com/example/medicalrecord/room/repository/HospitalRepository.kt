package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAOHospital
import com.example.medicalrecord.room.model.Hospital

class HospitalRepository(private val DAOHospital: DAOHospital) {
    val allHospitals: LiveData<List<Hospital>> = DAOHospital.getAllHospitals()

    @WorkerThread
    suspend fun insertHospital(hospital: Hospital) {
        DAOHospital.insertHospital(hospital)
    }

    @WorkerThread
    suspend fun insertHospitals(hospitals: List<Hospital>) {
        DAOHospital.insertHospitals(hospitals)
    }

    @WorkerThread
    suspend fun updateHospital(hospital: Hospital) {
        DAOHospital.updateHospital(hospital)
    }
}