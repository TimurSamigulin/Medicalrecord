package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.HospitalAdapter
import com.example.medicalrecord.adapter.impl.OnHospitalBtnClickListener
import com.example.medicalrecord.room.model.Hospital
import com.example.medicalrecord.viewmodel.HospitalViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_hospital.*
import kotlinx.android.synthetic.main.toolbar.*

class HospitalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnHospitalBtnClickListener {
    private lateinit var model: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital)
        setSupportActionBar(toolbar)
        setTitle("Больницы")

        model = ViewModelProvider(this).get(HospitalViewModel::class.java)

        val toggle = ActionBarDrawerToggle(this, hospital_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        hospital_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        hospital_nav_view.setNavigationItemSelectedListener(this)

        val hospitalAdapter = HospitalAdapter(this)
        val recyclerView: RecyclerView = findViewById(R.id.hospital_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = hospitalAdapter

        model.allHospital.observe(this, Observer {
            hospitals -> hospitals?.let { hospitalAdapter.setItem(it) }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_med_card -> {
                val intent = Intent(this, MedCardActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_doctor -> {
                val intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_drug -> {
                val intent = Intent(this, DrugActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_disease -> {
                val intent = Intent(this, DiseaseActivity::class.java)
                startActivity(intent)
            }
        }
        hospital_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onHospitalViewClickListener(hospital: Hospital) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
