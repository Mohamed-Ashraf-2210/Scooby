package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionResponse
import com.example.scooby.databinding.ItemAdaptionOneBinding
import com.example.scooby.utils.loadUrl

class PawsTopColAdapter(private val adaptionTopResponse: AdaptionResponse) : RecyclerView.Adapter<PawsTopColAdapter.PawsTopColViewHolder>() {
    inner class PawsTopColViewHolder(private val itemAdaptionOneBinding: ItemAdaptionOneBinding) :
        RecyclerView.ViewHolder(itemAdaptionOneBinding.root) {
            fun bind(item : AdaptionResponse.Data){
                item.petImage?.let { itemAdaptionOneBinding.imgItem.loadUrl(it) }
                itemAdaptionOneBinding.nameItem.text = item.name
                itemAdaptionOneBinding.titleItem.text = item.category
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PawsTopColViewHolder {
        return PawsTopColViewHolder(ItemAdaptionOneBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = adaptionTopResponse.data.size

    override fun onBindViewHolder(holder: PawsTopColViewHolder, position: Int) {
        holder.bind(adaptionTopResponse.data[position])
    }
}