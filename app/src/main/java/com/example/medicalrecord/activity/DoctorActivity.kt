package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.medicalrecord.R
import com.example.medicalrecord.fragmentadapter.DoctorFragmentAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_doctor.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*

class DoctorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)
        setSupportActionBar(toolbar)
        setTitle(R.string.doctor)


        val toggle = ActionBarDrawerToggle(this, doctor_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        doctor_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        doctor_nav_view.setNavigationItemSelectedListener(this)

        val fragmentAdapter = DoctorFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            TODO("Add fab listener")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_disease -> {
                val intent: Intent = Intent(this, DiseaseActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_drug -> {
                val intent: Intent = Intent(this, DrugActivity::class.java)
                startActivity(intent)
            }
        }
        doctor_drawer_layout.closeDrawer(GravityCompat.START)
        return true    }
}
