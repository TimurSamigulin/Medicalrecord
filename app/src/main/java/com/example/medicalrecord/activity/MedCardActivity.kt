package com.example.medicalrecord.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.medicalrecord.R
import com.example.medicalrecord.room.model.MedCard
import com.example.medicalrecord.viewmodel.MedCardViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_med_card.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class MedCardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var model: MedCardViewModel
    private val EDIT_MEDCARD_REQUEST = 1
    private val APP_PREFERENCES = "mysetting"
    private val APP_PREFERENCES_FIRST = "first"

    private lateinit var name: String
    private lateinit var lastname: String
    private lateinit var hight: String
    private lateinit var weight: String
    private lateinit var blood: String
    private lateinit var allergy: String
    private lateinit var sex: String
    private lateinit var city: String
    private lateinit var contact: String
    private lateinit var donor: String
    private var birthday: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_card)
        setSupportActionBar(toolbar)
        title = "Медицинская карта"

        val toggle = ActionBarDrawerToggle(this, med_card_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        med_card_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        med_card_nav_view.setNavigationItemSelectedListener(this)

        model = ViewModelProvider(this).get(MedCardViewModel::class.java)

        val mySetting: SharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        if (!mySetting.contains(APP_PREFERENCES_FIRST)) {
            model.insertMedCard(MedCard())
            val intent = Intent(this, AddMedCardActivity::class.java)
            startActivityForResult(intent, EDIT_MEDCARD_REQUEST)
        }
        model.medCard.observe(this, Observer { medCard ->
            name = medCard.name
            lastname = medCard.lastName
            hight = medCard.hight.toString()
            weight = medCard.weight.toString()
            blood = medCard.blood.toString()
            allergy = medCard.allergies
            sex = medCard.sex
            city = medCard.city
            contact = medCard.contact
            donor = medCard.donor
            birthday.timeInMillis = medCard.bithDate
            assignText()
        })
    }

    private fun assignText() {
        val txtName: TextView = findViewById(R.id.txt_mc_name)
        val txtLastName: TextView = findViewById(R.id.txt_mc_lastname)
        val txtHight: TextView = findViewById(R.id.txt_mc_hight)
        val txtWeight: TextView = findViewById(R.id.txt_mc_weight)
        val txtBlood: TextView = findViewById(R.id.txt_mc_blood)
        val txtAllergy: TextView = findViewById(R.id.txt_mc_allergies)
        val txtSex: TextView = findViewById(R.id.txt_mc_sex)
        val txtCity: TextView = findViewById(R.id.txt_mc_city)
        val txtContact: TextView = findViewById(R.id.txt_mc_contact)
        val txtDonor: TextView = findViewById(R.id.txt_mc_donor)
        val txtBirthday: TextView = findViewById(R.id.txt_mc_birthday)

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)

        txtName.text = name
        txtLastName.text = lastname
        txtHight.text = hight
        txtWeight.text = weight
        txtBlood.text = blood
        txtAllergy.text = allergy
        txtSex.text = sex
        txtCity.text = city
        txtContact.text = contact
        txtDonor.text = donor
        txtBirthday.text = formatter.format(birthday.time).toString()

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
            R.id.nav_menu_hospital -> {
                val intent = Intent(this, HospitalActivity::class.java)
                startActivity(intent)
            }
        }
        med_card_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mad_card_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.edit -> {
                val intent = Intent(this, AddMedCardActivity::class.java)
                intent.putExtra(AddMedCardActivity.EXTRA_NAME, name)
                intent.putExtra(AddMedCardActivity.EXTRA_LASTNAME, lastname)
                intent.putExtra(AddMedCardActivity.EXTRA_HIGHT, hight)
                intent.putExtra(AddMedCardActivity.EXTRA_WEIGHT, weight)
                intent.putExtra(AddMedCardActivity.EXTRA_BLOOD, blood)
                intent.putExtra(AddMedCardActivity.EXTRA_ALLERGY, allergy)
                intent.putExtra(AddMedCardActivity.EXTRA_SEX, sex)
                intent.putExtra(AddMedCardActivity.EXTRA_CONTACT, contact)
                intent.putExtra(AddMedCardActivity.EXTRA_CITY, city)
                intent.putExtra(AddMedCardActivity.EXTRA_DONOR, donor)
                intent.putExtra(AddMedCardActivity.EXTRA_BIRTHDAY, birthday.timeInMillis)

                startActivityForResult(intent, EDIT_MEDCARD_REQUEST)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == EDIT_MEDCARD_REQUEST) and (resultCode == Activity.RESULT_OK)) {
            val name = data?.getStringExtra(AddMedCardActivity.EXTRA_NAME) ?: ""
            val lastname = data?.getStringExtra(AddMedCardActivity.EXTRA_LASTNAME) ?: ""
            val hight = data?.getIntExtra(AddMedCardActivity.EXTRA_HIGHT, 0) ?: 0
            val weight = data?.getIntExtra(AddMedCardActivity.EXTRA_WEIGHT, 0) ?: 0
            val blood = data?.getIntExtra(AddMedCardActivity.EXTRA_BLOOD, 0) ?: 0
            val allergy = data?.getStringExtra(AddMedCardActivity.EXTRA_ALLERGY) ?: ""
            val sex = data?.getStringExtra(AddMedCardActivity.EXTRA_SEX) ?: "Муж."
            val city = data?.getStringExtra(AddMedCardActivity.EXTRA_CITY) ?: "Томск"
            val contact = data?.getStringExtra(AddMedCardActivity.EXTRA_CONTACT) ?: ""
            val donor = data?.getStringExtra(AddMedCardActivity.EXTRA_DONOR) ?: ""
            val birthday = data?.getLongExtra(AddMedCardActivity.EXTRA_BIRTHDAY, Calendar.getInstance().timeInMillis) ?: Calendar.getInstance().timeInMillis

            val mySetting: SharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            if(!mySetting.contains(APP_PREFERENCES_FIRST)) {
                val editor: SharedPreferences.Editor = mySetting.edit()
                editor.putBoolean(APP_PREFERENCES_FIRST, true)
                editor.apply()
            }

            val medCard = MedCard(null, name, lastname, birthday, hight, weight, blood, allergy, sex, city, contact, donor, "")
            model.insertMedCard(medCard)
        }
    }
}
