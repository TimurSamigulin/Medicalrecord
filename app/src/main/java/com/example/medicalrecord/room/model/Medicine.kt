package com.example.medicalrecord.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.medicalrecord.enumclass.UnitMedicine
import java.util.*

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Disease::class,
                                        parentColumns = arrayOf("id"),
                                        childColumns = arrayOf("disease_id"),
                                        onDelete = 5)))
class Medicine constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                           @ColumnInfo(name = "disease_id", index = true)
                           var diseaseId: Long?,
                           var title: String,
                           @ColumnInfo(name = "date_begin")
                           var dateBegin: Long,
                           @ColumnInfo(name = "date_end")
                           var dateEnd: Long?,
                           var day: Int,
                           var dose: Int,
                           var unit: String,
                           var info: String) {
    constructor():this(null, null,"", Calendar.getInstance().timeInMillis, null, 0, 0, UnitMedicine.PIECES.toString(), "")
}