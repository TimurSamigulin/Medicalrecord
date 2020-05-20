package com.example.medicalrecord.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.medicalrecord.room.model.Hospital

@Dao
interface DAOHospital {
    @Insert
    suspend fun insertHospital(hospital: Hospital)

    @Insert
    suspend fun insertHospitals(hospitals: List<Hospital>)

    @Update
    suspend fun updateHospital(hospital: Hospital)

    @Query("SELECT * FROM Hospital")
    fun getAllHospitals(): LiveData<List<Hospital>>
}