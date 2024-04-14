package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.scooby.databinding.ItemAdaptionAdoptBinding
import com.example.scooby.utils.loadUrl

class AdaptionAdoptMeAdapter(private val listOfData: AdaptionAdoptMeResponse) : RecyclerView.Adapter<AdaptionAdoptMeAdapter.AdaptionAdoptMeViewHolder>() {
    inner class AdaptionAdoptMeViewHolder(private val itemAdaptionAdoptBinding: ItemAdaptionAdoptBinding) :
        RecyclerView.ViewHolder(itemAdaptionAdoptBinding.root) {
        fun bind(item : AdaptionAdoptMeResponse.Data){
            item.petImage?.let {itemAdaptionAdoptBinding.imgItem.loadUrl(it)}
            itemAdaptionAdoptBinding.nameItem.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptionAdoptMeViewHolder {
        return AdaptionAdoptMeViewHolder(ItemAdaptionAdoptBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = listOfData.data.size

    override fun onBindViewHolder(holder: AdaptionAdoptMeViewHolder, position: Int) {
        holder.bind(listOfData.data[position])
    }
}