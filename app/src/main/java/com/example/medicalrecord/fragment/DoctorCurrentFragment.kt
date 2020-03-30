package com.example.medicalrecord.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.medicalrecord.R
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
