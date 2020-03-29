package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAODoctor
import com.example.medicalrecord.room.model.Doctor
import java.util.*

class DoctorRepository(private val daoDoctor: DAODoctor) {
    val currentDoctor: LiveData<List<Doctor>> = daoDoctor.getCurrentDoctors(Date().time)
    val oldDoctor: LiveData<List<Doctor>> = daoDoctor.getOldDoctors(Date().time)
    val allDoctor: LiveData<List<Doctor>> = daoDoctor.getAllDoctors()

    @WorkerThread
    suspend fun insertDoctor(doctor: Doctor){
        daoDoctor.insertDoctor(doctor)
    }

    @WorkerThread
    suspend fun updateDoctor(doctor: Doctor) {
        daoDoctor.updateDoctor(doctor)
    }

    @WorkerThread
    suspend fun deleteDoctor(doctor: Doctor) {
        daoDoctor.deleteDoctor(doctor)
    }
}