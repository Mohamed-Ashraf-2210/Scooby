package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionDogsResponse
import com.example.scooby.databinding.ItemAdaptionTwoBinding
import com.example.scooby.utils.loadUrl

class AdaptionDogsAdapter(private val adaptionDogsResponse: AdaptionDogsResponse) : RecyclerView.Adapter<AdaptionDogsAdapter.AdaptionDogsViewHolder>() {
    inner class AdaptionDogsViewHolder(private val itemAdaptionTwoBinding: ItemAdaptionTwoBinding) :
        RecyclerView.ViewHolder(itemAdaptionTwoBinding.root) {
        fun bind(item : AdaptionDogsResponse.Data){
            item.petImage?.let {itemAdaptionTwoBinding.imgItem.loadUrl(it)}
            itemAdaptionTwoBinding.nameItem.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptionDogsViewHolder {
        return AdaptionDogsViewHolder(ItemAdaptionTwoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = adaptionDogsResponse.data.size

    override fun onBindViewHolder(holder: AdaptionDogsViewHolder, position: Int) {
        holder.bind(adaptionDogsResponse.data[position])
    }
}