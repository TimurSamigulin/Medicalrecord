package com.example.medicalrecord.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.medicalrecord.R
import kotlinx.android.synthetic.main.toolbar.*

class DetailHospitalActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_TITLE = "detailhospitalactivity.TITLE"
        @JvmStatic
        val EXTRA_PHONE = "detailhospitalactivity.PHONE"
        @JvmStatic
        val EXTRA_ADDRESS = "detailhospitalactivity.ADDRESS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hospital)
        setSupportActionBar(toolbar)
        setTitle("Больница")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_map -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
