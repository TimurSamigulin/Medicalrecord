package com.example.medicalrecord.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medicalrecord.R
import com.example.medicalrecord.viewmodel.DiseaseViewModel

class AnalysisActivity : AppCompatActivity() {
    private lateinit var model: DiseaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)
    }
}
