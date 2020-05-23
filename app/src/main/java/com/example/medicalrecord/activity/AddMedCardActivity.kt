package com.example.medicalrecord.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.medicalrecord.R
import com.example.medicalrecord.room.model.MedCard
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class AddMedCardActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    companion object {
        @JvmStatic
        val EXTRA_ID = "addmedcardactivity.ID"
        @JvmStatic
        val EXTRA_NAME = "addmedcardactivity.NAME"
        @JvmStatic
        val EXTRA_LASTNAME = "addmedcardactivity.LASTNAME"
        @JvmStatic
        val EXTRA_HIGHT = "addmedcardactivity.HIGHT"
        @JvmStatic
        val EXTRA_WEIGHT = "addmedcardactivity.WEIGHT"
        @JvmStatic
        val EXTRA_BLOOD = "addmedcardactivity.BLOOD"
        @JvmStatic
        val EXTRA_ALLERGY = "addmedcardactivity.ALLERGY"
        @JvmStatic
        val EXTRA_SEX = "addmedcardactivity.SEX"
        @JvmStatic
        val EXTRA_CITY = "addmedcardactivity.CITY"
        @JvmStatic
        val EXTRA_CONTACT = "addmedcardactivity.CONTACT"
        @JvmStatic
        val EXTRA_DONOR = "addmedcardactivity.DONOR"
        @JvmStatic
        val EXTRA_BIRTHDAY = "addmedcardactivity.BIRTHDAY"
    }

    private lateinit var editName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editHight: EditText
    private lateinit var editWeight: EditText
    private lateinit var editBlood: EditText
    private lateinit var editAllergy: EditText
    private lateinit var editContact: EditText
    private lateinit var editDonor: EditText
    private lateinit var txtBirthday: TextView

    private var sex: String = "Муж."
    private var city: String = "Томск"

    private var birthday: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_med_card)
        setSupportActionBar(toolbar)
        title = "Создание медицинской карты"

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

        editName = findViewById(R.id.edt_mc_name)
        editLastName = findViewById(R.id.edt_mc_lastname)
        editHight = findViewById(R.id.edt_mc_hight)
        editWeight = findViewById(R.id.edt_mc_weight)
        editBlood = findViewById(R.id.edt_mc_blood)
        editAllergy = findViewById(R.id.edt_mc_allergies)
        editContact = findViewById(R.id.edt_mc_contact)
        editDonor = findViewById(R.id.edt_mc_donor)
        txtBirthday = findViewById(R.id.txt_mc_birthday)

        getSpinner()

        val intent: Intent = intent
        editName.setText(intent.getStringExtra(EXTRA_NAME) ?: "")
        editLastName.setText(intent.getStringExtra(EXTRA_LASTNAME) ?: "")
        editHight.setText(intent.getStringExtra(EXTRA_HIGHT) ?: "")
        editWeight.setText(intent.getStringExtra(EXTRA_WEIGHT) ?: "")
        editBlood.setText(intent.getStringExtra(EXTRA_BLOOD) ?: "")
        editAllergy.setText(intent.getStringExtra(EXTRA_ALLERGY) ?: "")
        editContact.setText(intent.getStringExtra(EXTRA_CONTACT) ?: "")
        editDonor.setText(intent.getStringExtra(EXTRA_DONOR) ?: "")
        birthday.timeInMillis = intent.getLongExtra(EXTRA_BIRTHDAY, Calendar.getInstance().timeInMillis)

        txtBirthday.text = simpleDateFormat.format(birthday.time).toString()
    }

    private fun saveMedCard() {
        val name = editName.text.toString()
        val lastname = editLastName.text.toString()
        val hightText = editHight.text.toString()
        val weightText = editWeight.text.toString()
        val bloodText = editBlood.text.toString()
        val allergy = editAllergy.text.toString()
        val donor = editDonor.text.toString()
        val contact = editContact.text.toString()
        val hight: Int
        val weight: Int
        val blood: Int

        try {
            hight = hightText.trim().toInt()
        }catch (e: NumberFormatException) {
            Toast.makeText(this, "Введите число в рост", Toast.LENGTH_LONG).show()
            return
        }

        try {
            weight = weightText.trim().toInt()
        }catch (e: NumberFormatException) {
            Toast.makeText(this, "Введите число в вес", Toast.LENGTH_LONG).show()
            return
        }

        try {
            blood = bloodText.trim().toInt()
        }catch (e: NumberFormatException) {
            Toast.makeText(this, "Введите число в группу крови", Toast.LENGTH_LONG).show()
            return
        }

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Заполните имя", Toast.LENGTH_LONG).show()
            return
        } else if (lastname.trim().isEmpty()) {
            Toast.makeText(this, "Заполните фамилию", Toast.LENGTH_LONG).show()
            return
        }

        val data = Intent()
        //val medCard = MedCard(null, name, lastname, birthday.timeInMillis, hight, weight, blood, allergy, disease, medicine, donor, "")
        data.putExtra(EXTRA_NAME, name)
        data.putExtra(EXTRA_LASTNAME, lastname)
        data.putExtra(EXTRA_HIGHT, hight)
        data.putExtra(EXTRA_WEIGHT, weight)
        data.putExtra(EXTRA_BLOOD, blood)
        data.putExtra(EXTRA_ALLERGY, allergy)
        data.putExtra(EXTRA_SEX, sex)
        data.putExtra(EXTRA_CITY, city)
        data.putExtra(EXTRA_CONTACT, contact)
        data.putExtra(EXTRA_DONOR, donor)
        data.putExtra(EXTRA_BIRTHDAY, birthday.timeInMillis)

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save -> {
                saveMedCard()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setBirthday(view: View) {
        DatePickerDialog(this, onBirthdayDateListener,
            birthday.get(Calendar.YEAR),
            birthday.get(Calendar.MONTH),
            birthday.get(Calendar.DAY_OF_MONTH)).show()
    }

    private val onBirthdayDateListener = DatePickerDialog.OnDateSetListener{
            _, year, month, day ->
        birthday.set(Calendar.YEAR, year)
        birthday.set(Calendar.MONTH, month)
        birthday.set(Calendar.DAY_OF_MONTH, day)

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        txtBirthday.text = simpleDateFormat.format(birthday.time).toString()
    }

    private fun getSpinner() {
        val sex = arrayListOf<String>("Муж.", "Жен.")
        val city = arrayListOf<String>("Томск")
        val spinnerSex = findViewById<Spinner>(R.id.edt_mc_sex)
        val spinnerCity = findViewById<Spinner>(R.id.edt_mc_city)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, sex)
        val adapterCity: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSex.adapter = adapter
        spinnerSex.onItemSelectedListener = this
        spinnerCity.adapter = adapterCity
        spinnerCity.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.edt_mc_sex -> {
                val item: String = parent.getItemAtPosition(position).toString()
                sex = item
            }
            R.id.edt_mc_city -> {
                val item: String = parent.getItemAtPosition(position).toString()
                city = item
            }
        }
    }
}
