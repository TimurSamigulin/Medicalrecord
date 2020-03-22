package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAODisease
import com.example.medicalrecord.room.model.Disease

class DiseaseRepository(private val DAODisease: DAODisease) {
    val currentDisease: LiveData<List<Disease>> = DAODisease.getCurrentDisease()
    val oldDisease: LiveData<List<Disease>> = DAODisease.getOldDisease()

    @WorkerThread
    suspend fun insertDisease(disease: Disease) {
        DAODisease.insertDisease(disease)
    }

    @WorkerThread
    suspend fun updateDisease(disease: Disease) {
        DAODisease.updateDisease(disease)
    }
}