package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.scooby.databinding.ItemPetShelterBinding
import com.example.scooby.utils.loadUrl


//second para : shelterData : ShelterResponse
class PetsShelterAdapter(private val petsShelterData: PetsInShelterResponse) : RecyclerView.Adapter<PetsShelterAdapter.PetsShelterViewHolder>() {
    inner class PetsShelterViewHolder(private val itemPetShelterBinding: ItemPetShelterBinding) :
        RecyclerView.ViewHolder(itemPetShelterBinding.root) {
        //second para : shelterData : ShelterResponse
        fun bind(itemPet : PetsInShelterResponse.PetsInShelter){
            itemPet.petImage?.let {itemPetShelterBinding.petImage.loadUrl(it)}
            itemPetShelterBinding.petTitle.text = itemPet.shelterInfo?.shelterName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsShelterViewHolder {
        return PetsShelterViewHolder(ItemPetShelterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = petsShelterData.petsInShelters.size

    override fun onBindViewHolder(holder: PetsShelterViewHolder, position: Int) {
        holder.bind(petsShelterData.petsInShelters[position])
        //second para : shelterData : ShelterResponse
    }
}