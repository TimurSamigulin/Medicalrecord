package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.Medicine
import com.example.medicalrecord.room.repository.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MedicineRepository
    val currentMedicine: LiveData<List<Medicine>>
    val oldMedicine: LiveData<List<Medicine>>
    val allMedicine: LiveData<List<Medicine>>

    init {
        val daoMedicine = DiseaseDatabase.getInstance(application, viewModelScope).daoMedicine()
        repository = MedicineRepository(daoMedicine)

        currentMedicine = repository.currentMedicine
        oldMedicine = repository.oldMedicine
        allMedicine = repository.allMedicine
    }

    fun insertMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicine(medicine)
        }
    }

    fun updateMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMedicine(medicine)
        }
    }

    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedicine(medicine)
        }
    }
}