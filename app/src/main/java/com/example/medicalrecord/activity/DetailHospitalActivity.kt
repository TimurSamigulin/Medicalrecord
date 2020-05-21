package com.example.medicalrecord.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medicalrecord.R

class DetailHospitalActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "detailhospitalactivity.TITLE"
        @JvmStatic
        val EXTRA_PHONE = "detailhospitalactivity.PHONE"
        @JvmStatic
        val EXTRA_ADDRESS = "detailhospitalactivity.ADDRESS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hospital)
    }
}
