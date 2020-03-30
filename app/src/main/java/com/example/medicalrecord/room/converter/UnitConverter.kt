package com.example.medicalrecord.room.converter

import androidx.room.TypeConverters
import com.example.medicalrecord.enumclass.UnitMedicine

class UnitConverter {
    @TypeConverters
    fun unitFromString(value: String?): UnitMedicine? {
        return if (value == null)
                    null
                else
                    when(value) {
                        UnitMedicine.PIECES.toString() -> UnitMedicine.PIECES
                        UnitMedicine.ML.toString() -> UnitMedicine.ML
                        else -> UnitMedicine.PIECES
                    }
    }

    @TypeConverters
    fun unitToString(value: UnitMedicine?): String? {
        return value.toString()
    }
}