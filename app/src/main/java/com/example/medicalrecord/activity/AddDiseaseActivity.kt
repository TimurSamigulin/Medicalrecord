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

class AddDiseaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "adddiseaseactivity.TITLE"
        val EXTRA_SYMPTOMS = "adddiseaseactivity.SYMPTOMS"
        val EXTRA_INFO = "adddiseaseactivity.INFO"
        val EXTRA_DATEBEGIN = "adddiseaseactivity.DATEBEGIN"
        val EXTRA_DATEEND = "adddiseaseactivity.DATEEND"
    }

    private lateinit var editTitle: EditText
    private lateinit var editSymptoms: EditText
    private lateinit var txtBeginDate: TextView
    //private lateinit var txtEndDate: TextView
    private lateinit var editInfo: EditText

    var beginDate: Calendar = Calendar.getInstance()
    //var endDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_disease)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Новое заболевание")

        editTitle = findViewById<EditText>(R.id.edt_disease_title)
        editSymptoms = findViewById<EditText>(R.id.edt_disease_symptoms)
        txtBeginDate = findViewById<TextView>(R.id.txt_disease_begin_date)
        //txtEndDate = findViewById<TextView>(R.id.txt_disease_end_date)
        editInfo = findViewById<EditText>(R.id.edt_disease_info)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtBeginDate.text = simpleDateFormat.format(beginDate.time).toString()
        //txtEndDate.text = simpleDateFormat.format(endDate.time).toString()
    }

    private fun saveDisease() {
        val title = editTitle.text.toString()
        val info = editInfo.text.toString()
        val symptoms = editSymptoms.text.toString()
        val dateBegin = beginDate.timeInMillis
        //val dateEnd = endDate.timeInMillis
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Заполните название болезни", Toast.LENGTH_LONG).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(AddDiseaseActivity.EXTRA_TITLE, title)
        data.putExtra(AddDiseaseActivity.EXTRA_SYMPTOMS, symptoms)
        data.putExtra(AddDiseaseActivity.EXTRA_INFO, info)
        data.putExtra(AddDiseaseActivity.EXTRA_DATEBEGIN, dateBegin)
        //data.putExtra(AddDiseaseActivity.EXTRA_DATEEND, dateEnd)

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save -> {saveDisease()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setBeginDate(view: View) {
        DatePickerDialog(this, onBeginDateListener,
                        beginDate.get(Calendar.YEAR),
                        beginDate.get(Calendar.MONTH),
                        beginDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    private val onBeginDateListener = DatePickerDialog.OnDateSetListener{
        view, year, month, day ->
        beginDate.set(Calendar.YEAR, year)
        beginDate.set(Calendar.MONTH, month)
        beginDate.set(Calendar.DAY_OF_MONTH, day)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtBeginDate.text = simpleDateFormat.format(beginDate.time).toString()
    }

    /*fun setEndDate(view: View) {
        DatePickerDialog(this, onEndDateListener,
                        endDate.get(Calendar.YEAR),
                        endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    val onEndDateListener = DatePickerDialog.OnDateSetListener{
        view, year, month, dayOfMonth ->
            endDate.set(Calendar.YEAR, year)
            endDate.set(Calendar.MONTH, month)
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
            txtEndDate.text = simpleDateFormat.format(endDate.time).toString()
    }*/
}
