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

    @Query("SELECT * FROM Disease WHERE dateEnd = null")
    fun getCurrentDisease(): LiveData<List<Disease>>

    @Query("SELECT * FROM Disease WHERE dateEnd != null")
    fun getOldDisease(): LiveData<List<Disease>>
}