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
import com.example.medicalrecord.fragmentadapter.DoctorFragmentAdapter
import com.example.medicalrecord.room.model.Doctor
import com.example.medicalrecord.viewmodel.DoctorViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class DoctorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val ADD_DOCTOR_REQUEST = 1
    private lateinit var model: DoctorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)
        setSupportActionBar(toolbar)
        setTitle(R.string.doctor)

        model = ViewModelProvider(this).get(DoctorViewModel::class.java)

        val toggle = ActionBarDrawerToggle(this, doctor_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        doctor_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        doctor_nav_view.setNavigationItemSelectedListener(this)

        val fragmentAdapter = DoctorFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            intent = Intent(this, AddDoctorActivity::class.java)
            startActivityForResult(intent, ADD_DOCTOR_REQUEST)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_med_card -> {
                val intent = Intent(this, MedCardActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_disease -> {
                val intent = Intent(this, DiseaseActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_drug -> {
                val intent = Intent(this, DrugActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_hospital -> {
                val intent = Intent(this, HospitalActivity::class.java)
                startActivity(intent)
            }
        }
        doctor_drawer_layout.closeDrawer(GravityCompat.START)
        return true    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DOCTOR_REQUEST && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(AddDoctorActivity.EXTRA_TITLE) ?: ""
            val hospital = data?.getStringExtra(AddDoctorActivity.EXTRA_HOSPITAL) ?: ""
            val cause = data?.getStringExtra(AddDoctorActivity.EXTRA_CAUSE) ?: ""
            val recipe = data?.getStringExtra(AddDoctorActivity.EXTRA_RECIPE) ?: ""
            val result = data?.getStringExtra(AddDoctorActivity.EXTRA_RESULT) ?: ""
            val visitDate = data?.getLongExtra(AddDoctorActivity.EXTRA_VISIT, Calendar.getInstance().timeInMillis) ?: Calendar.getInstance().timeInMillis

            val doctor = Doctor(null, null, title, cause, hospital, visitDate, result, recipe)
            model.insetDoctor(doctor)
            Toast.makeText(this, "Назначен визит к врачу", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Визит к врачу не назначен", Toast.LENGTH_SHORT).show()
        }
    }
}
