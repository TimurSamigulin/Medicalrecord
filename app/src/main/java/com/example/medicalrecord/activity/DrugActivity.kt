package com.example.medicalrecord.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.medicalrecord.R
import com.example.medicalrecord.fragmentadapter.DrugFragmentAdapter
import com.example.medicalrecord.room.model.Medicine
import com.example.medicalrecord.viewmodel.MedicineViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drug.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class DrugActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val ADD_MEDICINE_REQUEST = 1
    private lateinit var model: MedicineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug)
        setSupportActionBar(toolbar)
        setTitle(R.string.drug)

        model = ViewModelProvider(this).get(MedicineViewModel::class.java)

        val toggle = ActionBarDrawerToggle(this, drug_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drug_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        drug_nav_view.setNavigationItemSelectedListener(this)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddDrugActivity::class.java)
            startActivityForResult(intent, ADD_MEDICINE_REQUEST)
        }
      
        val fragmentAdapter = DrugFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)
      
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_doctor -> {
                val intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_disease -> {
                val intent = Intent(this, DiseaseActivity::class.java)
                startActivity(intent)
            }
        }
        drug_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_MEDICINE_REQUEST && resultCode == Activity.RESULT_OK){
            val title = data?.getStringExtra(AddDrugActivity.EXTRA_TITLE) ?: ""
            val dose = data?.getIntExtra(AddDrugActivity.EXTRA_DOSE, 0) ?: 0
            val day = data?.getIntExtra(AddDrugActivity.EXTRA_DAY, 0) ?: 0
            val unit = data?.getStringExtra(AddDrugActivity.EXTRA_UNIT) ?: ""
            val info = data?.getStringExtra(AddDrugActivity.EXTRA_INFO) ?: ""
            val beginDate = data?.getLongExtra(AddDrugActivity.EXTRA_BEGIN_DATE, Calendar.getInstance().timeInMillis) ?: Calendar.getInstance().timeInMillis

            val medicine = Medicine(null, null, title, beginDate, null, day, dose, unit, info)
            model.insertMedicine(medicine)

            Toast.makeText(this, "Добавлено лекарство", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Лекарствоо не добавлено", Toast.LENGTH_SHORT).show()
        }
    }
}
