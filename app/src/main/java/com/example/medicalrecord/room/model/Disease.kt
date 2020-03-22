package com.example.medicalrecord.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Disease constructor(@PrimaryKey(autoGenerate = true) var id: Long?,
                          var title: String,
                          var symptoms: String,
                          var dateBegin: Long?,
                          var dateEnd: Long?,
                          var info: String?){

}