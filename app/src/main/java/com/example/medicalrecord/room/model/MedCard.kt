package com.example.medicalrecord.room.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MedCard constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                          val name: String,
                          @ColumnInfo(name = "last_name")
                          val lastName: String,
                          @ColumnInfo(name = "bith_date")
                          val bithDate: Long,
                          val hight: Int,
                          val weight: Int,
                          val blood: Int,
                          val allergies: String,
                          val sex: String,
                          val city: String,
                          val contact: String,
                          val donor: String,
                          val photo_uri: String
)
{
    constructor():this(null, "Иван", "Иванов", 0, 0, 0, 0, "...", "Муж.", "Томск", "+79528892207", "Нет", "")
}