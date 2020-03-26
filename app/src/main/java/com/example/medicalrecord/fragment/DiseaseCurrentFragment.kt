package com.example.medicalrecord.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer

import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.DiseaseAdapter
import com.example.medicalrecord.adapter.impl.OnDiseaseAdapterBtnClickListener
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.viewmodel.DiseaseViewModel
import kotlinx.android.synthetic.main.fragment_disease_current.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DiseaseCurrentFragment : Fragment(), OnDiseaseAdapterBtnClickListener {
    private lateinit var model: DiseaseViewModel

    //Удалить потом
    @Suppress("DEPRECATION")
    private val dis = listOf(
        Disease(null, "Salam5", "Aleickum1", Date(1998, 3, 11), null, "qwerty"),
        Disease(null, "Salam6", "Aleickum2", Date(1998, 3, 12), null, "qwerty"),
        Disease(null, "Salam7", "Aleickum3", Date(1998, 3, 13), null, "qwerty"),
        Disease(null, "Salam8", "Aleickum4", Date(1998, 3, 14), Date(2020, 1, 14), "qwerty")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        model = ViewModelProvider(this).get(DiseaseViewModel::class.java)
        model.insertDisease(dis)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disease_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disease_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DiseaseAdapter(this@DiseaseCurrentFragment)
            model.currentDisease.observe(viewLifecycleOwner, Observer {
                diseases -> diseases?.let { (adapter as DiseaseAdapter).setItems(it) }
            })
        }
    }

    override fun onDiseaseViewClickListener(disease: Disease) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
