package com.example.medicalrecord.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Disease constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                          var title: String,
                          var symptoms: String,
                          var dateBegin: Long,
                          var dateEnd: Long?,
                          var info: String?) {
    constructor():this(null, "", "", Calendar.getInstance().timeInMillis, null, "")
}