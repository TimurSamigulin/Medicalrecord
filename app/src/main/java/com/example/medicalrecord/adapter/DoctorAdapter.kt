package com.example.medicalrecord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.impl.OnDoctorBtnClickListener
import com.example.medicalrecord.room.model.Doctor
import kotlinx.android.synthetic.main.doctor_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class DoctorAdapter(val listener: OnDoctorBtnClickListener): RecyclerView.Adapter<DoctorAdapter.DoctorHolder>() {
    private var doctors: List<Doctor> = listOf<Doctor>()

    inner class DoctorHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textTitle: TextView
        val textVisit: TextView
        val textHospital: TextView
        init {
            textTitle = itemView.findViewById(R.id.doctor_title)
            textHospital = itemView.findViewById(R.id.doctor_hospital)
            textVisit = itemView.findViewById(R.id.doctor_date)
        }
        fun bindView(doctor: Doctor, listener: OnDoctorBtnClickListener) {
            itemView.setOnClickListener{(listener.onDoctorViewClickListener(doctor))}
        }
    }

    fun setItems(doctors: List<Doctor>) {
        this.doctors = doctors
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item, parent, false)
        return DoctorHolder(itemView)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    override fun onBindViewHolder(holder: DoctorHolder, position: Int) {
        val currentDoctor = doctors[position]
        holder.textTitle.text = currentDoctor.title
        holder.textHospital.text = currentDoctor.hospital
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentDoctor.visitDate
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
        holder.textVisit.text = formatter.format(calendar.time).toString()

        holder.bindView(currentDoctor, listener)
    }
}