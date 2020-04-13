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
import androidx.core.view.isVisible
import com.example.medicalrecord.R
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AddDiseaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_ID = "adddiseaseactivity.ID"
        @JvmStatic
        val EXTRA_TITLE = "adddiseaseactivity.TITLE"
        @JvmStatic
        val EXTRA_SYMPTOMS = "adddiseaseactivity.SYMPTOMS"
        @JvmStatic
        val EXTRA_INFO = "adddiseaseactivity.INFO"
        @JvmStatic
        val EXTRA_DATE_BEGIN = "adddiseaseactivity.DATEBEGIN"
        @JvmStatic
        val EXTRA_COURSE = "adddiseaseactivity.COURSE"
        @JvmStatic
        val EXTRA_DATE_END = "adddiseaseactivity.DATE_END"
    }

    private lateinit var editTitle: EditText
    private lateinit var editSymptoms: EditText
    private lateinit var txtBeginDate: TextView
    private lateinit var txtEndDate: TextView
    private lateinit var txtEnd: TextView
    private lateinit var editInfo: EditText
    private lateinit var editCouse: EditText

    private var beginDate: Calendar = Calendar.getInstance()
    private var endDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_disease)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Новое заболевание"

        editTitle = findViewById(R.id.edt_disease_title)
        editSymptoms = findViewById(R.id.edt_disease_symptoms)
        txtBeginDate = findViewById(R.id.txt_disease_begin_date)
        txtEndDate = findViewById(R.id.txt_disease_end_date)
        txtEnd = findViewById(R.id.txt_disease_end)
        editInfo = findViewById(R.id.edt_disease_info)
        editCouse = findViewById(R.id.edt_disease_course)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

        val intent: Intent = intent
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Редактирование болезни"

            editTitle.setText(intent.getStringExtra(EXTRA_TITLE))
            editSymptoms.setText(intent.getStringExtra(EXTRA_SYMPTOMS))
            editInfo.setText(intent.getStringExtra(EXTRA_INFO))
            editCouse.setText(intent.getStringExtra(EXTRA_COURSE))
            beginDate.timeInMillis = intent.getLongExtra(EXTRA_DATE_BEGIN, 0)
            if (intent.hasExtra(EXTRA_DATE_END) and (intent.getLongExtra(EXTRA_DATE_END, 0) != 0.toLong())) {
                txtEnd.text = "Дата выздоровления"
                endDate.timeInMillis = intent.getLongExtra(EXTRA_DATE_END, 0)
                txtEndDate.text = simpleDateFormat.format(endDate.time).toString()

                txtEndDate.isClickable = true
                txtEndDate.visibility = View.VISIBLE
                txtEnd.visibility = View.VISIBLE
            }
        }

        txtBeginDate.text = simpleDateFormat.format(beginDate.time).toString()
    }

    private fun saveDisease() {
        val title = editTitle.text.toString()
        val info = editInfo.text.toString()
        val symptoms = editSymptoms.text.toString()
        val dateBegin = beginDate.timeInMillis
        val course = editCouse.text.toString()
        val dateEnd = endDate.timeInMillis

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Заполните название болезни", Toast.LENGTH_LONG).show()
            return
        }

        val intent: Intent = intent
        val id = intent.getLongExtra(EXTRA_ID, -1)

        val data = Intent()
        if (id != (-1).toLong()) {
            data.putExtra(EXTRA_ID, id)
        }
        if (txtEndDate.isVisible) {
            data.putExtra(EXTRA_DATE_END, dateEnd)
        }

        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_SYMPTOMS, symptoms)
        data.putExtra(EXTRA_INFO, info)
        data.putExtra(EXTRA_DATE_BEGIN, dateBegin)
        data.putExtra(EXTRA_COURSE, course)

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

    fun setBeginDate(@Suppress("UNUSED_PARAMETER")view: View) {
        DatePickerDialog(this, onBeginDateListener,
                        beginDate.get(Calendar.YEAR),
                        beginDate.get(Calendar.MONTH),
                        beginDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    private val onBeginDateListener = DatePickerDialog.OnDateSetListener{
            _, year, month, day ->
        beginDate.set(Calendar.YEAR, year)
        beginDate.set(Calendar.MONTH, month)
        beginDate.set(Calendar.DAY_OF_MONTH, day)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtBeginDate.text = simpleDateFormat.format(beginDate.time).toString()
    }

    fun setEndDate(@Suppress("UNUSED_PARAMETER")view: View) {
        DatePickerDialog(this, onEndDateListener,
                        endDate.get(Calendar.YEAR),
                        endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    private val onEndDateListener = DatePickerDialog.OnDateSetListener{
            _, year, month, dayOfMonth ->
            endDate.set(Calendar.YEAR, year)
            endDate.set(Calendar.MONTH, month)
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
            txtEndDate.text = simpleDateFormat.format(endDate.time).toString()
    }
}
