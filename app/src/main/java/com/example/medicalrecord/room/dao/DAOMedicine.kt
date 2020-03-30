package com.example.medicalrecord.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medicalrecord.room.model.Medicine

@Dao
interface DAOMedicine {
    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Insert
    suspend fun insertMedicines(medicines: List<Medicine>)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * FROM Medicine")
    fun getAllMedicine(): LiveData<List<Medicine>>

    @Query("SELECT * FROM Medicine WHERE (date_end >= :curDate OR date_end = null)")
    fun getCurrentMedicine(curDate: Long): LiveData<List<Medicine>>

    @Query("SELECT * FROM Medicine WHERE date_end < :curDate")
    fun getOldMedicine(curDate: Long): LiveData<List<Medicine>>
}