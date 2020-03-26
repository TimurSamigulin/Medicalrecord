package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.DiseaseAdapter
import com.example.medicalrecord.adapter.impl.OnDiseaseAdapterBtnClickListener
import com.example.medicalrecord.fragmentadapter.DiseaseFragmentAdapter
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.viewmodel.DiseaseViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*

class DiseaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle("Болезни")

        val toggle = ActionBarDrawerToggle(this, disease_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        disease_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        disease_nav_view.setNavigationItemSelectedListener(this)

        val fragmentAdapter = DiseaseFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_doctor -> {
                val intent: Intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_drug -> {
                val intent: Intent = Intent(this, DrugActivity::class.java)
                startActivity(intent)
            }
        }
        disease_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
