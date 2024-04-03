package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.FragmentShopBinding

class ShopAdapter() :RecyclerView.Adapter<ShopAdapter.MedicineViewHolder>() {
    class MedicineViewHolder(val binding: FragmentShopBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = FragmentShopBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MedicineViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}