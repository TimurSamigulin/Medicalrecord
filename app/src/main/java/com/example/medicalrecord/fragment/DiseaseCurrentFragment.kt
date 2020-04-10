package com.example.medicalrecord.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer

import com.example.medicalrecord.R
import com.example.medicalrecord.activity.AddDiseaseActivity
import com.example.medicalrecord.activity.DetailDiseaseActivity
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
        Disease(null, "q", "Aleickum1", 0, Calendar.getInstance().timeInMillis - 1000, "qwerty", "dsads")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        model = ViewModelProvider(this).get(DiseaseViewModel::class.java)
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
        val intent = Intent(activity, DetailDiseaseActivity::class.java)
        intent.putExtra(AddDiseaseActivity.EXTRA_ID, disease.id)
        intent.putExtra(AddDiseaseActivity.EXTRA_TITLE, disease.title)
        intent.putExtra(AddDiseaseActivity.EXTRA_COURSE, disease.course)
        intent.putExtra(AddDiseaseActivity.EXTRA_DATE_BEGIN, disease.dateBegin)
        intent.putExtra(AddDiseaseActivity.EXTRA_DATE_END, disease.dateEnd)
        intent.putExtra(AddDiseaseActivity.EXTRA_SYMPTOMS, disease.symptoms)
        intent.putExtra(AddDiseaseActivity.EXTRA_INFO, disease.info)
        startActivity(intent)
    }
}
