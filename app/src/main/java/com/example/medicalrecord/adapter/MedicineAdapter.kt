package com.example.medicalrecord.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalrecord.R
import com.example.medicalrecord.adapter.impl.OnMedicineAdapterBtnClickListener
import com.example.medicalrecord.room.model.Medicine
import java.text.SimpleDateFormat
import java.util.*

class MedicineAdapter(val listener: OnMedicineAdapterBtnClickListener): RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {
    private var medicines: List<Medicine> = listOf()


    inner class MedicineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView
        val textDateBegin: TextView
        val textDose: TextView
        val textUnit: TextView

        init {
            textTitle = itemView.findViewById(R.id.medicine_title)
            textDateBegin = itemView.findViewById(R.id.medicine_date_begin)
            textDose = itemView.findViewById(R.id.medicine_dose)
            textUnit = itemView.findViewById(R.id.medicine_unit)
        }

        fun bindView(medicine: Medicine, listener: OnMedicineAdapterBtnClickListener) {
            itemView.setOnClickListener { (listener.onMedicineViewClickListener(medicine)) }
        }
    }

    fun setItems(medicine: List<Medicine>) {
        this.medicines = medicine
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medicine_item, parent, false)
        return MedicineViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return medicines.size
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val currentMedicine = medicines[position]

        holder.textTitle.text = currentMedicine.title
        holder.textDose.text = currentMedicine.dose.toString()
        holder.textUnit.text = currentMedicine.unit

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentMedicine.dateBegin
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        holder.textDateBegin.text = formatter.format(calendar.time).toString()

        holder.bindView(currentMedicine, listener)
    }
}