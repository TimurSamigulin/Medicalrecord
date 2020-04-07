package com.example.medicalrecord.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.medicalrecord.R
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AddDoctorActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "adddoctoractivity.TITLE"
        val EXTRA_HOSPITAL = "adddoctoractivity.HOSPITAL"
        val EXTRA_CAUSE = "adddoctoractivity.CAUSE"
        val EXTRA_VISIT = "adddoctoractivity.VISIT"
        val EXTRA_RECIPE = "adddoctoractivity.RECIPE"
        val EXTRA_RESULT = "adddoctoractivity.RESULT"
    }

    private lateinit var editTitle: EditText
    private lateinit var editHospital: EditText
    private lateinit var editCause: EditText
    private lateinit var editRecipe: EditText
    private lateinit var editResult: EditText
    private lateinit var txtVisitDate: TextView

    var visitDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Визит к доктору")

        editTitle = findViewById(R.id.edt_medicine_title)
        editHospital = findViewById(R.id.edt_doctor_hospital)
        editCause = findViewById(R.id.edt_doctor_cause)
        editRecipe = findViewById(R.id.edt_doctor_recipe)
        editResult = findViewById(R.id.edt_doctor_result)
        txtVisitDate = findViewById(R.id.txt_doctor_visit)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtVisitDate.text = simpleDateFormat.format(visitDate.time).toString()

    }

    private fun saveDoctor() {
        val title = editTitle.text.toString()
        val hospital = editHospital.text.toString()
        val cause = editCause.text.toString()
        val recipe = editRecipe.text.toString()
        val result = editResult.text.toString()
        val vD = visitDate.timeInMillis

        if(title.trim().isEmpty() && hospital.trim().isEmpty()) {
            Toast.makeText(this, "Заполните поля врача и больницы", Toast.LENGTH_LONG).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_HOSPITAL, hospital)
        data.putExtra(EXTRA_CAUSE, cause)
        data.putExtra(EXTRA_RECIPE, recipe)
        data.putExtra(EXTRA_RESULT, result)
        data.putExtra(EXTRA_VISIT, vD)

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save -> {saveDoctor()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setVisitDate(view: View) {
        DatePickerDialog(this, onVisitDateListener,
            visitDate.get(Calendar.YEAR),
            visitDate.get(Calendar.MONTH),
            visitDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    val onVisitDateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        visitDate.set(Calendar.YEAR, year)
        visitDate.set(Calendar.MONTH, month)
        visitDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtVisitDate.text = simpleDateFormat.format(visitDate.time).toString()
    }
}
