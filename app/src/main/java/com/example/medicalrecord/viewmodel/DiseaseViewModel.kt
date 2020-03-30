package com.example.medicalrecord.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.dao.DAODisease
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.room.repository.DiseaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DiseaseViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DiseaseRepository
    val currentDisease: LiveData<List<Disease>>
    val oldDisease: LiveData<List<Disease>>
    val allDisease: LiveData<List<Disease>>

    init {
        val daoDisease: DAODisease = DiseaseDatabase.getInstance(application, viewModelScope).DAODisease()
        repository = DiseaseRepository(daoDisease)

        currentDisease = repository.currentDisease
        oldDisease = repository.oldDisease
        allDisease = repository.allDisease
    }

    fun insertDiseases(diseases: List<Disease>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDiseases(diseases)
        }
    }

    fun insertDisease(diseases: Disease) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDisease(diseases)
        }
    }

    fun updateDisease(diseases: Disease) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDisease(diseases)
        }
    }

    fun deleteDisease(diseases: Disease) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDisease(diseases)
        }
    }

}