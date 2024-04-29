package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.rescue.ShelterResponse
import com.example.scooby.databinding.ItemShelterBinding
import com.example.scooby.scooby.paws.fragment.PawsFragmentDirections
import com.example.scooby.utils.loadUrl

class ShelterAdapter(private val shelterData: ShelterResponse):RecyclerView.Adapter<ShelterAdapter.ShelterViewHolder>() {
    inner class ShelterViewHolder(private val itemShelterBinding: ItemShelterBinding):RecyclerView.ViewHolder(itemShelterBinding.root){
        fun bind(itemShelter : ShelterResponse.AllShelter ){
            itemShelter.shelterImage?.let { itemShelterBinding.shelterImage.loadUrl(it) }
            itemShelterBinding.shelterName.text = itemShelter.shelterName
            itemShelterBinding.rate.rating = itemShelter.rate?.toFloat() ?: TODO()
            itemShelterBinding.shelterNumRate.text = itemShelter.rate.toString()
            itemShelterBinding.numLikeShelter.text = itemShelter.numberOfRates.toString()
            itemShelterBinding.shelterTiming.text = itemShelter.description
            itemShelterBinding.shelterAddress.text = itemShelter.locations?.address.toString()
            navigate2ShelterProfile(itemShelter)
        }

        private fun navigate2ShelterProfile(shelterData: ShelterResponse.AllShelter){
            itemShelterBinding.shelterImage.setOnClickListener {
                val action = shelterData.id?.let { shelterInfo ->
                    PawsFragmentDirections.actionPawsFragmentToShelterProfileFragment(
                        shelterInfo
                    )
                }
                if (action != null) {
                    it.findNavController().navigate(action)
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelterViewHolder {
        return ShelterViewHolder(ItemShelterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = shelterData.allShelters.size

    override fun onBindViewHolder(holder: ShelterViewHolder, position: Int) {
        holder.bind(shelterData.allShelters[position])
    }
}