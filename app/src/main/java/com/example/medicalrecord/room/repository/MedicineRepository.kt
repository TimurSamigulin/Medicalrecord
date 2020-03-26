package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAOMedicine
import com.example.medicalrecord.room.model.Medicine
import java.util.*

class MedicineRepository(private val daoMedicine: DAOMedicine) {
    val currentMedicine: LiveData<List<Medicine>> = daoMedicine.getCurrentMedicine(Date().time)
    val oldMedicine: LiveData<List<Medicine>> = daoMedicine.getOldMedicine(Date().time)
    val allMedicine: LiveData<List<Medicine>> = daoMedicine.getAllMedicine()

    @WorkerThread
    suspend fun insertMedicine(medicine: Medicine) {
        daoMedicine.insertMedicine(medicine)
    }

    @WorkerThread
    suspend fun updateMedicine(medicine: Medicine) {
        daoMedicine.updateMedicine(medicine)
    }

    @WorkerThread
    suspend fun deleteMedicine(medicine: Medicine) {
        daoMedicine.deleteMedicine(medicine)
    }
}