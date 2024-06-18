package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.missing.DogsResponse
import com.example.scooby.databinding.ItemPetMissingBinding
import com.example.scooby.utils.loadUrl

class DogsMissingAdapter(private val dogsRes: DogsResponse) :
    RecyclerView.Adapter<DogsMissingAdapter.DogsMissingViewHolder>() {
    inner class DogsMissingViewHolder(private val itemBinding: ItemPetMissingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DogsResponse.Dog) {
            item.petImage?.let {
                itemBinding.petImage.loadUrl(it)
            }
            itemBinding.petDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsMissingViewHolder {
        return DogsMissingViewHolder(
            ItemPetMissingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = dogsRes.dogs.size

    override fun onBindViewHolder(holder: DogsMissingViewHolder, position: Int) {
        holder.bind(dogsRes.dogs[position])
    }
}