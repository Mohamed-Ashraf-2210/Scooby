package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionCatsResponse
import com.example.scooby.databinding.ItemAdaptionTwoBinding
import com.example.scooby.utils.loadUrl

class AdaptionCatsAdapter(private val adaptionCatsResponse: AdaptionCatsResponse) : RecyclerView.Adapter<AdaptionCatsAdapter.AdaptionCatsViewHolder>() {
    inner class AdaptionCatsViewHolder(private val itemAdaptionTwoBinding: ItemAdaptionTwoBinding) :
        RecyclerView.ViewHolder(itemAdaptionTwoBinding.root) {
        fun bind(item : AdaptionCatsResponse.Data){
            item.petImage?.let {itemAdaptionTwoBinding.imgItem.loadUrl(it)}
            itemAdaptionTwoBinding.nameItem.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptionCatsViewHolder {
        return AdaptionCatsViewHolder(ItemAdaptionTwoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = adaptionCatsResponse.data.size

    override fun onBindViewHolder(holder: AdaptionCatsViewHolder, position: Int) {
        holder.bind(adaptionCatsResponse.data[position])
    }
}