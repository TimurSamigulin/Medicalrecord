package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.medicalrecord.R
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class DetailDoctorActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "DetailDoctorActivity.TITLE"
        @JvmStatic
        val EXTRA_CAUSE = "DetailDoctorActivity.CAUSE"
        @JvmStatic
        val EXTRA_HOSPITAL = "DetailDoctorActivity.HOSPITAL"
        @JvmStatic
        val EXTRA_DATE_VISIT = "DetailDoctorActivity.DATE_VISIT"
        @JvmStatic
        val EXTRA_RESULT = "DetailDoctorActivity.RESULT"
        @JvmStatic
        val EXTRA_RECIPE = "DetailDoctorActivity.RECIPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doctor)
        setSupportActionBar(toolbar)
        title = "Посещение к врачу"

        val intent: Intent = intent

        val txtTitle: TextView = findViewById(R.id.txt_detail_doctor_title)
        val txtCause: TextView = findViewById(R.id.txt_detail_doctor_cause)
        val txtHospital: TextView = findViewById(R.id.txt_detail_doctor_hospital)
        val txtVisitDate: TextView = findViewById(R.id.txt_detail_doctor_visit)
        val txtResult: TextView = findViewById(R.id.txt_detail_doctor_result)
        val txtRecipe: TextView = findViewById(R.id.txt_detail_doctor_recipe)

        txtTitle.text = intent.getStringExtra(EXTRA_TITLE) ?: ""
        txtCause.text = intent.getStringExtra(EXTRA_CAUSE) ?: ""
        txtHospital.text = intent.getStringExtra(EXTRA_HOSPITAL) ?: ""
        txtResult.text = intent.getStringExtra(EXTRA_RESULT) ?: ""
        txtRecipe.text = intent.getStringExtra(EXTRA_RECIPE) ?: ""

        val visitDate: Long = intent.getLongExtra(EXTRA_DATE_VISIT, 0)

        if(visitDate != 0.toLong()) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = visitDate
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            txtVisitDate.text = formatter.format(calendar.time).toString()
        } else {
            txtVisitDate.text = "..."
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.edit -> {
                TODO("edit activity")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
