package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.missing.CatsResponse
import com.example.scooby.databinding.ItemPetMissingBinding
import com.example.scooby.utils.loadUrl

class CatsMissingAdapter(private val catsRes: CatsResponse) :
    RecyclerView.Adapter<CatsMissingAdapter.CatsMissingViewHolder>() {
    inner class CatsMissingViewHolder(private val itemBinding: ItemPetMissingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: CatsResponse.ShuffledCat) {
            item.petImage?.let {
                itemBinding.petImage.loadUrl(it)
            }
            itemBinding.petDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsMissingViewHolder {
        return CatsMissingViewHolder(
            ItemPetMissingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = catsRes.shuffledCats.size

    override fun onBindViewHolder(holder: CatsMissingViewHolder, position: Int) {
            holder.bind(catsRes.shuffledCats[position])
    }
}