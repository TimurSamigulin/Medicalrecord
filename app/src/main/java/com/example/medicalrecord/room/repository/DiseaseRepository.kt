package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAODisease
import com.example.medicalrecord.room.model.Disease
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class DiseaseRepository(private val DAODisease: DAODisease) {
    val currentDisease: LiveData<List<Disease>> = DAODisease.getCurrentDisease(Calendar.getInstance().timeInMillis)
    val oldDisease: LiveData<List<Disease>> = DAODisease.getOldDisease(Calendar.getInstance().timeInMillis)
    val allDisease: LiveData<List<Disease>> = DAODisease.getAllDisease()

    @WorkerThread
    suspend fun insertDisease(disease: Disease) {
        DAODisease.insertDisease(disease)
    }

    @WorkerThread
    suspend fun insertDiseases(diseases: List<Disease>) {
        DAODisease.insertDiseases(diseases)
    }

    @WorkerThread
    suspend fun updateDisease(disease: Disease) {
        DAODisease.updateDisease(disease)
}
}