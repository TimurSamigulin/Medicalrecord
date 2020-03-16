package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.medicalrecord.R
import com.example.medicalrecord.fragmentadapter.DrugFragmentAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drug.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*

class DrugActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug)
        setSupportActionBar(toolbar)
        setTitle(R.string.drug)

        val toggle = ActionBarDrawerToggle(this, drug_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drug_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        drug_nav_view.setNavigationItemSelectedListener(this)

        val fragmentAdapter = DrugFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_doctor -> {
                val intent: Intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_disease -> {
                val intent: Intent = Intent(this, DiseaseActivity::class.java)
                startActivity(intent)
            }
        }
        drug_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
