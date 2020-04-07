package com.example.medicalrecord.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalrecord.R
import com.example.medicalrecord.enumclass.UnitMedicine
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AddDrugActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "adddiseaseactivity.TITLE"
        val EXTRA_BEGIN_DATE = "adddiseaseactivity.BEGIN_DATE"
        val EXTRA_DOSE = "adddiseaseactivity.DOSE"
        val EXTRA_UNIT = "adddiseaseactivity.UNIT"
        val EXTRA_DAY = "adddiseaseactivity.DAY"
        val EXTRA_INFO = "adddiseaseactivity.INFO"
    }

    private lateinit var editTitle: EditText
    private lateinit var editDose: EditText
    private lateinit var editDay: EditText
    private lateinit var editInfo: EditText
    private lateinit var txtBeginDate: TextView

    private lateinit var unitMedicine: String
    var beginDate: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drug)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Новое лекарство")

        getSpinner()
        editTitle = findViewById(R.id.edt_medicine_title)
        editDose = findViewById(R.id.edt_medicine_dose)
        editDay = findViewById(R.id.edt_medicine_day)
        editInfo = findViewById(R.id.edt_medicine_info)
        txtBeginDate = findViewById(R.id.txt_medicine_begin_day)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtBeginDate.text = simpleDateFormat.format(beginDate.time).toString()
    }

    private fun saveMedicine() {
        val title = editTitle.text.toString()
        val info = editInfo.text.toString()
        val day = editDay.text.toString()
        val dose = editDose.text.toString()
        if(title.trim().isEmpty() && day.trim().isEmpty() && dose.trim().isEmpty()) {
            Toast.makeText(this, "Заполните поля: лекарство, доза, количество дней", Toast.LENGTH_LONG).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DAY, day)
        data.putExtra(EXTRA_BEGIN_DATE, beginDate.timeInMillis)
        data.putExtra(EXTRA_DOSE, dose)
        data.putExtra(EXTRA_INFO, info)
        data.putExtra(EXTRA_UNIT, unitMedicine)

        setResult(Activity.RESULT_OK, data)
        finish()
    }


    fun setMedicineBeginDay(@Suppress("UNUSED_PARAMETER")view: View) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save -> {saveMedicine()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getSpinner() {
        val unitMedicine = arrayListOf<String>(UnitMedicine.PIECES.toString(), UnitMedicine.ML.toString())
        val spinnerUnit = findViewById<Spinner>(R.id.spinner_medicine_unit)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitMedicine)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnit.adapter = adapter
        spinnerUnit.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item: String = parent?.getItemAtPosition(position).toString()
        unitMedicine = item
    }
}
