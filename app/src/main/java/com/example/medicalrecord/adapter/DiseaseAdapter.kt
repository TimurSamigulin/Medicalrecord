package com.example.medicalrecord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.impl.OnDiseaseAdapterBtnClickListener
import com.example.medicalrecord.room.model.Disease
import java.text.SimpleDateFormat
import java.util.*

class DiseaseAdapter(val listener: OnDiseaseAdapterBtnClickListener): RecyclerView.Adapter<DiseaseAdapter.DiseaseHolder>() {
    private var diseases: List<Disease> = listOf()

    inner class DiseaseHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView
        val textBegin: TextView
        init {
            textTitle = itemView.findViewById(R.id.disease_title)
            textBegin = itemView.findViewById(R.id.disease_begin_date)
        }
        fun bindView(disease: Disease, listener: OnDiseaseAdapterBtnClickListener) {
            itemView.setOnClickListener{(listener.onDiseaseViewClickListener(disease))}
        }
    }

    fun setItems(diseases: List<Disease>) {
        this.diseases = diseases
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.disease_item, parent, false)
        return DiseaseHolder(itemView)
    }

    override fun getItemCount(): Int {
        return diseases.size
    }

    override fun onBindViewHolder(holder: DiseaseHolder, position: Int) {
        val currentDisease: Disease = diseases[position]
        holder.textTitle.text = currentDisease.title

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentDisease.dateBegin
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        holder.textBegin.text = formatter.format(calendar.time).toString()

        holder.bindView(currentDisease, listener)
    }
}