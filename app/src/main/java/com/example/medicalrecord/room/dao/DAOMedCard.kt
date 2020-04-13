package com.example.medicalrecord.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medicalrecord.room.model.MedCard


@Dao
interface DAOMedCard {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedCard(medCard: MedCard)

    @Update
    suspend fun updateMedCard(medCard: MedCard)

    @Delete
    suspend fun deleteMedCard(medCard: MedCard)

    @Query("SELECT * FROM MedCard")
    fun gedAllMedCard(): MedCard

    @Query("SELECT * FROM MedCard WHERE id = :id")
    fun gedMedCard(id: Long): LiveData<MedCard>

    @Query("DELETE FROM MedCard")
    fun deleteAllMedCard()
}