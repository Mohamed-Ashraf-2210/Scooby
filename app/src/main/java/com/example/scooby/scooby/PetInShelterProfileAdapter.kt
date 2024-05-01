package com.example.scooby.scooby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PetShelterProfileResponse
import com.example.scooby.databinding.ItemPetBinding
//import com.example.scooby.utils.loadUrl

class PetInShelterProfileAdapter(
    private val petsData : List<PetShelterProfileResponse.PetShelterProfileResponseItem>
) : RecyclerView.Adapter<PetInShelterProfileAdapter.PetInShelterProfileViewHolder>() {

    inner class PetInShelterProfileViewHolder(private val itemPetBinding: ItemPetBinding) :
        RecyclerView.ViewHolder(itemPetBinding.root) {
        fun bind(petsData: PetShelterProfileResponse.PetShelterProfileResponseItem) {
            //petsData.petImage?.let { itemPetBinding.ivPet.loadUrl(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetInShelterProfileViewHolder {
        return PetInShelterProfileViewHolder(
            ItemPetBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = petsData.size

    override fun onBindViewHolder(holder: PetInShelterProfileViewHolder, position: Int) {
        holder.bind(petsData[position])
    }
}