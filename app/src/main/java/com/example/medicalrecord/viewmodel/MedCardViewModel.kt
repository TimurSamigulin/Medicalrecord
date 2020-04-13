package com.example.medicalrecord.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicalrecord.room.dao.DAOMedCard
import com.example.medicalrecord.room.database.DiseaseDatabase
import com.example.medicalrecord.room.model.MedCard
import com.example.medicalrecord.room.repository.MedCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedCardViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MedCardRepository
    val medCard: LiveData<MedCard>

    init {
        val daoMedCard: DAOMedCard = DiseaseDatabase.getInstance(application, viewModelScope).daoMedCard()
        repository = MedCardRepository(daoMedCard)

        insertMedCard(MedCard(null, "name", "lastname", 231232, 142, 23, 1, "ew", "", "", "", ""))

        medCard = repository.getMedCard()
    }

    fun insertMedCard(medCard: MedCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedCard(medCard)
        }
    }
}