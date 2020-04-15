package com.example.medicalrecord.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.medicalrecord.room.dao.DAOMedCard
import com.example.medicalrecord.room.model.MedCard

class MedCardRepository(private val daoMedCard: DAOMedCard) {
    private val medCardId: Long = 0

    @WorkerThread
    suspend fun insertMedCard(medCard: MedCard) {
        medCard.id = medCardId
        daoMedCard.insertMedCard(medCard)
    }

    @WorkerThread
    suspend fun deleteAllMedCard(){
        daoMedCard.deleteAllMedCard()
    }


    fun getMedCard(): LiveData<MedCard> {
        return daoMedCard.gedMedCard(medCardId)
    }
}