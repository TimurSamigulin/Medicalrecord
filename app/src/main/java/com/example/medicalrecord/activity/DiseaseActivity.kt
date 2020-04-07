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
import com.example.medicalrecord.fragmentadapter.DiseaseFragmentAdapter
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.viewmodel.DiseaseViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_disease.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class DiseaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val ADD_DISEASE_REQUEST = 1
    private lateinit var model: DiseaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle("Болезни")

        model = ViewModelProvider(this).get(DiseaseViewModel::class.java)

        val toggle = ActionBarDrawerToggle(this, disease_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        disease_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        disease_nav_view.setNavigationItemSelectedListener(this)

        val fragmentAdapter = DiseaseFragmentAdapter(supportFragmentManager)
        viewpager_disease.adapter = fragmentAdapter

        tabs_disease.setupWithViewPager(viewpager_disease)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddDiseaseActivity::class.java)
            startActivityForResult(intent, ADD_DISEASE_REQUEST)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_menu_doctor -> {
                val intent = Intent(this, DoctorActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_menu_drug -> {
                val intent = Intent(this, DrugActivity::class.java)
                startActivity(intent)
            }
        }
        disease_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DISEASE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(AddDiseaseActivity.EXTRA_TITLE) ?: ""
            val symptoms = data?.getStringExtra(AddDiseaseActivity.EXTRA_SYMPTOMS) ?: ""
            val info = data?.getStringExtra(AddDiseaseActivity.EXTRA_INFO) ?: ""
            val dateBegin = data?.getLongExtra(AddDiseaseActivity.EXTRA_DATE_BEGIN, Calendar.getInstance().timeInMillis) ?: Calendar.getInstance().timeInMillis
            //val dateEnd = data?.getLongExtra(AddDiseaseActivity.EXTRA_DATEEND, 0)

            val disease = Disease(null, title, symptoms, dateBegin, null, info)
            model.insertDisease(disease)

            Toast.makeText(this, "Добавлена болезнь", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Болезнь не сохранена", Toast.LENGTH_SHORT).show()
        }
    }

}
