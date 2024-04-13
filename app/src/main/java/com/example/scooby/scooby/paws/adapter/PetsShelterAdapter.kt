package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.rescue.PetsShelterResponse
import com.example.scooby.databinding.ItemPetShelterBinding
import com.example.scooby.utils.loadUrl

class PetsShelterAdapter(private val petsShelterData: PetsShelterResponse) : RecyclerView.Adapter<PetsShelterAdapter.PetsShelterViewHolder>() {
    inner class PetsShelterViewHolder(private val itemPetShelterBinding: ItemPetShelterBinding) :
        RecyclerView.ViewHolder(itemPetShelterBinding.root) {
        fun bind(itemPet : PetsShelterResponse.PetsInShelter){
            itemPet.petImage?.let {itemPetShelterBinding.petImage.loadUrl(it)}
            itemPetShelterBinding.petTitle.text = itemPet.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsShelterViewHolder {
        return PetsShelterViewHolder(ItemPetShelterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = petsShelterData.petsInShelters.size

    override fun onBindViewHolder(holder: PetsShelterViewHolder, position: Int) {
        holder.bind(petsShelterData.petsInShelters[position])
    }
}