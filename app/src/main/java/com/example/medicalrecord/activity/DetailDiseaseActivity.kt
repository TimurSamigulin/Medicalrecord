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
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.viewmodel.DetailDiseaseViewModel
import kotlinx.android.synthetic.main.toolbar.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class DetailDiseaseActivity : AppCompatActivity() {
    private val EDIT_DISEASE_REQUEST = 1

    private lateinit var model: DetailDiseaseViewModel

    private var dId: Long = -1
    private lateinit var dTitle: String
    private lateinit var dSymptoms: String
    private lateinit var dCourse: String
    private lateinit var dInfo: String
    private var beginDate: Long = 0
    private var endDate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_disease)
        setSupportActionBar(toolbar)
        title = "Заболевание"

        val intent: Intent = intent

        dTitle = intent.getStringExtra(AddDiseaseActivity.EXTRA_TITLE) ?: ""
        dSymptoms = intent.getStringExtra(AddDiseaseActivity.EXTRA_SYMPTOMS) ?: ""
        dCourse = intent.getStringExtra(AddDiseaseActivity.EXTRA_INFO) ?: ""
        dInfo = intent.getStringExtra(AddDiseaseActivity.EXTRA_COURSE) ?: ""
        beginDate = intent.getLongExtra(AddDiseaseActivity.EXTRA_DATE_BEGIN, 0)
        endDate = intent.getLongExtra(AddDiseaseActivity.EXTRA_DATE_END, 0)
        dId = intent.getLongExtra(AddDiseaseActivity.EXTRA_ID, -1)

        model = ViewModelProvider(this).get(DetailDiseaseViewModel::class.java)
        assignText()
    }

    private fun assignText() {
        val txtTitle: TextView = findViewById(R.id.txt_detail_disease_title)
        val txtBeginDate: TextView = findViewById(R.id.txt_detail_disease_begin_date)
        val txtSymptoms: TextView = findViewById(R.id.txt_detail_disease_symptoms)
        val txtCourse: TextView = findViewById(R.id.txt_detail_disease_course)
        val txtInfo: TextView = findViewById(R.id.txt_detail_disease_info)
        val txtEndDate: TextView = findViewById(R.id.txt_detail_disease_end_date)

        txtTitle.text = dTitle
        txtSymptoms.text = dSymptoms
        txtCourse.text = dCourse
        txtInfo.text = dInfo

        if(beginDate != 0.toLong()) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = beginDate
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            txtBeginDate.text = formatter.format(calendar.time).toString()
        } else {
            txtBeginDate.text = "..."
        }

        if(endDate != 0.toLong()) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = endDate
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            txtEndDate.text = formatter.format(calendar.time).toString()
        } else {
            txtEndDate.text = "..."
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.edit -> {
                val intent = Intent(this, AddDiseaseActivity::class.java)

                intent.putExtra(AddDiseaseActivity.EXTRA_ID, dId)
                intent.putExtra(AddDiseaseActivity.EXTRA_TITLE, dTitle)
                intent.putExtra(AddDiseaseActivity.EXTRA_SYMPTOMS, dSymptoms)
                intent.putExtra(AddDiseaseActivity.EXTRA_INFO, dInfo)
                intent.putExtra(AddDiseaseActivity.EXTRA_DATE_BEGIN, beginDate)
                intent.putExtra(AddDiseaseActivity.EXTRA_DATE_END, endDate)
                intent.putExtra(AddDiseaseActivity.EXTRA_COURSE, dCourse)

                startActivityForResult(intent, EDIT_DISEASE_REQUEST)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == EDIT_DISEASE_REQUEST) and (resultCode == Activity.RESULT_OK)) {
            val id: Long = data?.getLongExtra(AddDiseaseActivity.EXTRA_ID, -1) ?: -1

            if (id == (-1).toLong()) {
                Toast.makeText(this, "Запись о болезни не обновлена", Toast.LENGTH_SHORT).show()
                return
            }

            val title = data?.getStringExtra(AddDiseaseActivity.EXTRA_TITLE) ?: ""
            val symptoms = data?.getStringExtra(AddDiseaseActivity.EXTRA_SYMPTOMS) ?: ""
            val info = data?.getStringExtra(AddDiseaseActivity.EXTRA_INFO) ?: ""
            val course = data?.getStringExtra(AddDiseaseActivity.EXTRA_COURSE) ?: ""
            val dateBegin = data?.getLongExtra(AddDiseaseActivity.EXTRA_DATE_BEGIN, Calendar.getInstance().timeInMillis) ?: Calendar.getInstance().timeInMillis
            val dateEnd = data?.getLongExtra(AddDiseaseActivity.EXTRA_DATE_END, 0)

            val disease = Disease(id, title, symptoms, dateBegin, dateEnd, info, course)
            model.updateDisease(disease)

            Toast.makeText(this, "Запись о болезни обновлена", Toast.LENGTH_SHORT).show()
            updateText(disease)
        } else {
            Toast.makeText(this, "Запись о болезни не обновлена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateText(disease: Disease) {
        dTitle = disease.title
        dSymptoms = disease.symptoms
        dCourse = disease.course ?: ""
        dInfo = disease.info ?: ""
        beginDate = disease.dateBegin
        endDate = disease.dateEnd ?: 0
        dId = disease.id ?: -1
        assignText()
    }
}
