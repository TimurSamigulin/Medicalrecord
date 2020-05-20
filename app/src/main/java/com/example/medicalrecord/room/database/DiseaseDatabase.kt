package com.example.medicalrecord.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medicalrecord.room.converter.DateConverter
import com.example.medicalrecord.room.converter.UnitConverter
import com.example.medicalrecord.room.dao.*
import com.example.medicalrecord.room.model.*
import kotlinx.coroutines.CoroutineScope

@Database(entities = [(Disease::class), (Doctor::class), (Medicine::class), (MedCard::class), (Hospital::class)], version = 1, exportSchema = false)
@TypeConverters((DateConverter::class))
abstract class DiseaseDatabase: RoomDatabase() {
    abstract fun DAODisease(): DAODisease
    abstract fun daoDoctor(): DAODoctor
    abstract fun daoMedicine(): DAOMedicine
    abstract fun daoMedCard(): DAOMedCard
    abstract fun daoHospital(): DAOHospital

    companion object {
        @Volatile
        private var INSTANCE: DiseaseDatabase? = null

        fun getInstance(context: Context, @Suppress("UNUSED_PARAMETER")scope: CoroutineScope): DiseaseDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiseaseDatabase::class.java,
                    "medCard.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}