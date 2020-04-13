package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.medicalrecord.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_med_card.*
import kotlinx.android.synthetic.main.toolbar.*

class MedCardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_card)
        setSupportActionBar(toolbar)
        title = "Медицинская карточка"

        val toggle = ActionBarDrawerToggle(this, med_card_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        med_card_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        med_card_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_disease -> {
                val intent = Intent(this, DiseaseActivity::class.java)
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
        }
        med_card_drawer_layout.closeDrawer(GravityCompat.START)
        return true    }
}
