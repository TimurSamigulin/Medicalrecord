package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.medicalrecord.R
import kotlinx.android.synthetic.main.toolbar.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class DetailDiseaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "DetailDiseaseActivity.TITLE"
        @JvmStatic
        val EXTRA_SYMPTOMS = "DetailDiseaseActivity.SYMPTOMS"
        @JvmStatic
        val EXTRA_INFO = "DetailDiseaseActivity.INFO"
        @JvmStatic
        val EXTRA_DATE_BEGIN = "DetailDiseaseActivity.DATE_BEGIN"
        @JvmStatic
        val EXTRA_COURSE = "DetailDiseaseActivity.COURSE"
        @JvmStatic
        val EXTRA_DATE_END = "DetailDiseaseActivity.DATE_END"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_disease)
        setSupportActionBar(toolbar)
        title = "Заболевание"

        val intent: Intent = intent

        val txtTitle: TextView = findViewById(R.id.txt_detail_disease_title)
        val txtBeginDate: TextView = findViewById(R.id.txt_detail_disease_begin_date)
        val txtSymptoms: TextView = findViewById(R.id.txt_detail_disease_symptoms)
        val txtCourse: TextView = findViewById(R.id.txt_detail_disease_course)
        val txtInfo: TextView = findViewById(R.id.txt_detail_disease_info)
        val txtEndDate: TextView = findViewById(R.id.txt_detail_disease_end_date)

        txtTitle.text = intent.getStringExtra(EXTRA_TITLE) ?: ""
        txtSymptoms.text = intent.getStringExtra(EXTRA_SYMPTOMS) ?: ""
        txtInfo.text = intent.getStringExtra(EXTRA_INFO) ?: ""
        txtCourse.text = intent.getStringExtra(EXTRA_COURSE) ?: ""

        val beginDate: Long = intent.getLongExtra(EXTRA_DATE_BEGIN, 0)
        val endDate: Long = intent.getLongExtra(EXTRA_DATE_END, 0)

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
                TODO("edit activity")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
