package com.example.medicalrecord.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medicalrecord.room.model.Doctor
import java.util.*

@Dao
interface DAODoctor {
    @Insert
    suspend fun insertDoctor(doctor: Doctor)

    @Insert
    suspend fun insertDoctors(doctors: List<Doctor>)

    @Update
    suspend fun updateDoctor(doctor: Doctor)

    @Delete
    suspend fun deleteDoctor(doctor: Doctor)

    @Query("SELECT * FROM Doctor")
    fun getAllDoctors(): LiveData<List<Doctor>>

    @Query("SELECT * FROM Doctor WHERE visit_date >= :curDate or visit_date = null")
    fun getCurrentDoctors(curDate: Long): LiveData<List<Doctor>>

    @Query("SELECT * FROM Doctor WHERE visit_date < :curDate")
    fun getOldDoctors(curDate: Long): LiveData<List<Doctor>>
}