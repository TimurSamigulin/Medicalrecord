package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.dao.DAODisease
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.room.repository.DiseaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailDiseaseViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DiseaseRepository

    init {
        val daoDisease: DAODisease = DiseaseDatabase.getInstance(application, viewModelScope).DAODisease()
        repository = DiseaseRepository(daoDisease)
    }

    fun updateDisease(diseases: Disease) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDisease(diseases)
        }
    }
}