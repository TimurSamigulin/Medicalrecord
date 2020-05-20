package com.example.medicalrecord.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Hospital constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                           val title: String,
                           val address: String,
                           val phone: String?) {
    constructor():this(null, "", "", "")
}