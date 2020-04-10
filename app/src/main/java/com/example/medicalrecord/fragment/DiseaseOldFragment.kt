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
import com.example.medicalrecord.activity.DetailDiseaseActivity
import com.example.medicalrecord.adapter.DiseaseAdapter
import com.example.medicalrecord.adapter.impl.OnDiseaseAdapterBtnClickListener
import com.example.medicalrecord.room.model.Disease
import com.example.medicalrecord.viewmodel.DiseaseViewModel
import kotlinx.android.synthetic.main.fragment_disease_current.*

/**
 * A simple [Fragment] subclass.
 */
class DiseaseOldFragment : Fragment(), OnDiseaseAdapterBtnClickListener {
    private lateinit var model: DiseaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        model = ViewModelProvider(this).get(DiseaseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disease_old, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disease_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DiseaseAdapter(this@DiseaseOldFragment)
            model.allDisease.observe(viewLifecycleOwner, Observer {
                    diseases -> diseases?.let { (adapter as DiseaseAdapter).setItems(it) }
            })
        }
    }

    override fun onDiseaseViewClickListener(disease: Disease) {
        val intent = Intent(activity, DetailDiseaseActivity::class.java)
        intent.putExtra(DetailDiseaseActivity.EXTRA_TITLE, disease.title)
        intent.putExtra(DetailDiseaseActivity.EXTRA_COURSE, disease.course)
        intent.putExtra(DetailDiseaseActivity.EXTRA_DATE_BEGIN, disease.dateBegin)
        intent.putExtra(DetailDiseaseActivity.EXTRA_DATE_END, disease.dateEnd)
        intent.putExtra(DetailDiseaseActivity.EXTRA_SYMPTOMS, disease.symptoms)
        intent.putExtra(DetailDiseaseActivity.EXTRA_INFO, disease.info)
        startActivity(intent)
    }

}
