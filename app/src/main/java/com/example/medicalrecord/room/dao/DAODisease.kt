package com.example.medicalrecord.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medicalrecord.room.model.Disease

@Dao
interface DAODisease {
    @Insert
    suspend fun insertDisease(disease: Disease)

    @Insert
    suspend fun insertDiseases(diseases: List<Disease>)

    @Update
    suspend fun updateDisease(disease: Disease)

    @Delete
    suspend fun deleteDisease(disease: Disease)

    @Query("SELECT * FROM Disease")
    fun getAllDisease(): LiveData<List<Disease>>

    @Query("SELECT * FROM Disease WHERE date_end = 0 OR date_end >= :time OR date_end = null")
    fun getCurrentDisease(time: Long): LiveData<List<Disease>>

    @Query("SELECT * FROM Disease WHERE date_end != 0 and date_end <= :time")
    fun getOldDisease(time: Long): LiveData<List<Disease>>
}