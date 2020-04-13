package com.example.medicalrecord.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.medicalrecord.R
import com.example.medicalrecord.activity.AddDoctorActivity
import com.example.medicalrecord.activity.DetailDoctorActivity
import com.example.medicalrecord.adapter.DoctorAdapter
import com.example.medicalrecord.adapter.impl.OnDoctorBtnClickListener
import com.example.medicalrecord.room.model.Doctor
import com.example.medicalrecord.viewmodel.DoctorViewModel
import kotlinx.android.synthetic.main.fragment_doctor_current.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DoctorCurrentFragment : Fragment(), OnDoctorBtnClickListener {
    private  lateinit var model: DoctorViewModel

    private val doc = Doctor(null, null, "Terapect", "Prosto", "3", Calendar.getInstance().timeInMillis + 100000, "123", "jivi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        model = ViewModelProvider(this).get(DoctorViewModel::class.java)
        model.insetDoctor(doc)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctor_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DoctorAdapter(this@DoctorCurrentFragment)
            model.currentDoctor.observe(viewLifecycleOwner, Observer {
                doctors -> doctors.let { (adapter as DoctorAdapter).setItems(doctors) }
            })
        }
    }

    override fun onDoctorViewClickListener(doctor: Doctor) {
        val intent = Intent(activity, DetailDoctorActivity::class.java)
        intent.putExtra(AddDoctorActivity.EXTRA_TITLE, doctor.title)
        intent.putExtra(AddDoctorActivity.EXTRA_CAUSE, doctor.cause)
        intent.putExtra(AddDoctorActivity.EXTRA_HOSPITAL, doctor.hospital)
        intent.putExtra(AddDoctorActivity.EXTRA_VISIT, doctor.visitDate)
        intent.putExtra(AddDoctorActivity.EXTRA_RESULT, doctor.result)
        intent.putExtra(AddDoctorActivity.EXTRA_RECIPE, doctor.recipe)
        intent.putExtra(AddDoctorActivity.EXTRA_ID, doctor.id)
        startActivity(intent)
    }


}
