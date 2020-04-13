package com.example.medicalrecord.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medicalrecord.room.model.MedCard

interface DAOMedCard {
    @Insert
    suspend fun insertMedCard(medCard: MedCard)

    @Update
    suspend fun updateMedCard(medCard: MedCard)

    @Delete
    suspend fun deleteMedCard(medCard: MedCard)

    @Query("SELECT * FROM MedCard")
    fun gedMedCard(): MedCard

    @Query("DELETE * FROM MedCard")
    fun deleteAllMedCard()
}