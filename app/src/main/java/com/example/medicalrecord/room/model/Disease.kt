package com.example.medicalrecord.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Disease constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                          var title: String,
                          var symptoms: String,
                          @ColumnInfo(name = "date_begin")
                          var dateBegin: Long,
             //             @ColumnInfo(name = "date_end")
                          var dateEnd: Long?,
                          var info: String?,
                          var course: String?) {
    constructor():this(null, "", "", Calendar.getInstance().timeInMillis, null, "", "")
}