package com.example.medicalrecord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.impl.OnHospitalBtnClickListener
import com.example.medicalrecord.room.model.Hospital

class HospitalAdapter(val listener: OnHospitalBtnClickListener): RecyclerView.Adapter<HospitalAdapter.HospitalHolder>() {
    private var hospitals: List<Hospital> = listOf()

    inner class HospitalHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView
        val textNumber: TextView
        init {
            textTitle = itemView.findViewById(R.id.hospital_title)
            textNumber = itemView.findViewById(R.id.hospital_phone)
        }
        fun bindView(hospital: Hospital, listener: OnHospitalBtnClickListener) {
            itemView.setOnClickListener{(listener.onHospitalViewClickListener(hospital))}
        }
    }

    fun setItem(hospitals: List<Hospital>) {
        this.hospitals = hospitals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hospital_item, parent, false)
        return HospitalHolder(itemView)
    }

    override fun getItemCount(): Int {
        return hospitals.size
    }

    override fun onBindViewHolder(holder: HospitalHolder, position: Int) {
        val currentHospital: Hospital = hospitals[position]
        holder.textTitle.text = currentHospital.title
        holder.textNumber.text = currentHospital.phone

        holder.bindView(currentHospital, listener)
    }

}