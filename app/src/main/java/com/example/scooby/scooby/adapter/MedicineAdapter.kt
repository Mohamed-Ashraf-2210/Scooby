package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.FragmentMedicineBinding

class MedicineAdapter() :RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {
    class MedicineViewHolder(val binding: FragmentMedicineBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = FragmentMedicineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MedicineViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}