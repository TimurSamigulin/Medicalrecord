package com.example.medicalrecord.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Disease::class,
                                        parentColumns = arrayOf("id"),
                                        childColumns = arrayOf("disease_id"),
                                        onDelete = 5)))
class Doctor constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                         @ColumnInfo(name = "disease_id", index = true)
                         var diseaseId: Long?,
                         var title: String,
                         var cause: String,
                         var hospital: String,
                         @ColumnInfo(name = "visit_date")
                         var visitDate: Long,
                         var result: String,
                         var recipe: String) {
    constructor():this(null, null, "", "", "", Calendar.getInstance().timeInMillis, "", "")
}