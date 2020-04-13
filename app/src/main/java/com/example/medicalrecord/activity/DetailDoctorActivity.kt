package com.example.medicalrecord.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.medicalrecord.R
import com.example.medicalrecord.room.model.Doctor
import com.example.medicalrecord.viewmodel.DoctorViewModel
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class DetailDoctorActivity : AppCompatActivity() {
    private val EDIT_DOCTOR_REQUEST = 1
    private lateinit var model: DoctorViewModel

    private var dId: Long = -1
    private lateinit var dTitle: String
    private lateinit var dCause: String
    private lateinit var dHospital: String
    private lateinit var dResult: String
    private lateinit var dRecipe: String
    private var dVisitDate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doctor)
        setSupportActionBar(toolbar)
        title = "Посещение к врачу"

        model = ViewModelProvider(this).get(DoctorViewModel::class.java)

        val intent: Intent = intent

        dTitle = intent.getStringExtra(AddDoctorActivity.EXTRA_TITLE) ?: ""
        dCause = intent.getStringExtra(AddDoctorActivity.EXTRA_CAUSE) ?: ""
        dHospital = intent.getStringExtra(AddDoctorActivity.EXTRA_HOSPITAL) ?: ""
        dResult = intent.getStringExtra(AddDoctorActivity.EXTRA_RESULT) ?: ""
        dRecipe = intent.getStringExtra(AddDoctorActivity.EXTRA_RECIPE) ?: ""
        dVisitDate = intent.getLongExtra(AddDoctorActivity.EXTRA_VISIT, 0)

        assignText()
    }

    private fun assignText() {
        val txtTitle: TextView = findViewById(R.id.txt_detail_doctor_title)
        val txtCause: TextView = findViewById(R.id.txt_detail_doctor_cause)
        val txtHospital: TextView = findViewById(R.id.txt_detail_doctor_hospital)
        val txtVisitDate: TextView = findViewById(R.id.txt_detail_doctor_visit)
        val txtResult: TextView = findViewById(R.id.txt_detail_doctor_result)
        val txtRecipe: TextView = findViewById(R.id.txt_detail_doctor_recipe)

        txtTitle.text = dTitle
        txtCause.text = dCause
        txtHospital.text = dHospital
        txtResult.text = dResult
        txtRecipe.text = dRecipe

        if(dVisitDate != 0.toLong()) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dVisitDate
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
                val intent = Intent(this, AddDoctorActivity::class.java)

                intent.putExtra(AddDoctorActivity.EXTRA_ID, dId)
                intent.putExtra(AddDoctorActivity.EXTRA_VISIT, dVisitDate)
                intent.putExtra(AddDoctorActivity.EXTRA_RESULT, dResult)
                intent.putExtra(AddDoctorActivity.EXTRA_RECIPE, dRecipe)
                intent.putExtra(AddDoctorActivity.EXTRA_CAUSE, dCause)
                intent.putExtra(AddDoctorActivity.EXTRA_HOSPITAL, dHospital)
                intent.putExtra(AddDoctorActivity.EXTRA_TITLE, dTitle)

                startActivityForResult(intent, EDIT_DOCTOR_REQUEST)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == EDIT_DOCTOR_REQUEST) and (resultCode == Activity.RESULT_OK)) {
            val id: Long = data?.getLongExtra(AddDoctorActivity.EXTRA_ID, -1) ?: -1

            if (id == (-1).toLong()) {
                Toast.makeText(this, "Запись о докторе не обновлена", Toast.LENGTH_SHORT).show()
                return
            }

            dTitle = data?.getStringExtra(AddDoctorActivity.EXTRA_TITLE) ?: ""
            dCause = data?.getStringExtra(AddDoctorActivity.EXTRA_CAUSE) ?: ""
            dHospital = data?.getStringExtra(AddDoctorActivity.EXTRA_HOSPITAL) ?: ""
            dRecipe = data?.getStringExtra(AddDoctorActivity.EXTRA_RECIPE) ?: ""
            dResult = data?.getStringExtra(AddDoctorActivity.EXTRA_RESULT) ?: ""
            dVisitDate = data?.getLongExtra(AddDoctorActivity.EXTRA_VISIT, 0) ?: 0
            dId = id

            val doctor: Doctor = Doctor(dId, null, dTitle, dCause, dHospital, dVisitDate, dResult, dRecipe)
            model.updateDoctor(doctor)

            Toast.makeText(this, "Запись о докторе обновлена", Toast.LENGTH_SHORT).show()
            assignText()
        } else {
            Toast.makeText(this, "Запись о докторе не обновлена", Toast.LENGTH_SHORT).show()
        }
    }
}
