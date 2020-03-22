package com.example.medicalrecord.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medicalrecord.room.converter.DateConverter
import com.example.medicalrecord.room.dao.DAODisease
import com.example.medicalrecord.room.model.Disease
import kotlinx.coroutines.CoroutineScope

@Database(entities = [(Disease::class)], version = 1)
@TypeConverters(DateConverter::class)
abstract class DiseaseDatabase: RoomDatabase() {
    abstract fun DAODisease(): DAODisease


    companion object {
        @Volatile
        private var INSTANCE: DiseaseDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): DiseaseDatabase {
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