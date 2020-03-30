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
import com.example.medicalrecord.adapter.MedicineAdapter
import com.example.medicalrecord.adapter.impl.OnMedicineAdapterBtnClickListener
import com.example.medicalrecord.room.model.Medicine
import com.example.medicalrecord.viewmodel.MedicineViewModel
import kotlinx.android.synthetic.main.fragment_drug_old.*

/**
 * A simple [Fragment] subclass.
 */
class DrugOldFragment : Fragment(), OnMedicineAdapterBtnClickListener {
    private lateinit var model: MedicineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        model = ViewModelProvider(this).get(MedicineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_old, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicine_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MedicineAdapter(this@DrugOldFragment)
            model.oldMedicine.observe(viewLifecycleOwner, Observer {
                    medicines -> medicines?.let { (adapter as MedicineAdapter).setItems(it) }
            })
        }
    }


    override fun onMedicineViewClickListener(medicine: Medicine) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
