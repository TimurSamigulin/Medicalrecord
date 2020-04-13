package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.medicalrecord.R
import com.example.medicalrecord.viewmodel.MedCardViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_med_card.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class MedCardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var model: MedCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_card)
        setSupportActionBar(toolbar)
        title = "Медицинская карточка"

        val toggle = ActionBarDrawerToggle(this, med_card_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        med_card_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        med_card_nav_view.setNavigationItemSelectedListener(this)

        val txtName: TextView = findViewById(R.id.txt_mc_name)
        val txtLastName: TextView = findViewById(R.id.txt_mc_lastname)
        val txtHight: TextView = findViewById(R.id.txt_mc_hight)
        val txtWeight: TextView = findViewById(R.id.txt_mc_weight)
        val txtBlood: TextView = findViewById(R.id.txt_mc_blood)
        val txtAllergy: TextView = findViewById(R.id.txt_mc_allergies)
        val txtDisease: TextView = findViewById(R.id.txt_mc_important_disease)
        val txtMedicine: TextView = findViewById(R.id.txt_mc_important_medicine)
        val txtDonor: TextView = findViewById(R.id.txt_mc_donor)
        val txtBirthday: TextView = findViewById(R.id.txt_mc_birthday)


        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        model = ViewModelProvider(this).get(MedCardViewModel::class.java)
        model.medCard.observe(this, Observer { medCard ->
            txtName.text = medCard.name
            txtLastName.text = medCard.lastName
            txtHight.text = medCard.hight.toString()
            txtWeight.text = medCard.weight.toString()
            txtBlood.text = medCard.blood.toString()
            txtAllergy.text = medCard.allergies
            txtDisease.text = medCard.importDisease
            txtMedicine.text = medCard.importMedicine
            txtDonor.text = medCard.donor

            val birthday: Calendar = Calendar.getInstance()
            birthday.timeInMillis = medCard.bithDate
            txtBirthday.text = formatter.format(birthday.time).toString()
        })

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
