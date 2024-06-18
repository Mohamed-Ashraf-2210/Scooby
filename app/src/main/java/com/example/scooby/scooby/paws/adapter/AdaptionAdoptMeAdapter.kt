package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.scooby.databinding.ItemAdaptionAdoptBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.utils.loadUrl
import com.varunest.sparkbutton.SparkEventListener

class AdaptionAdoptMeAdapter(private val listOfData: AdaptionAdoptMeResponse, val userId:String?, val pawsViewModel: PawsViewModel) : RecyclerView.Adapter<AdaptionAdoptMeAdapter.AdaptionAdoptMeViewHolder>() {
    inner class AdaptionAdoptMeViewHolder(private val itemAdaptionAdoptBinding: ItemAdaptionAdoptBinding) :
        RecyclerView.ViewHolder(itemAdaptionAdoptBinding.root) {
        fun bind(item : AdaptionAdoptMeResponse.Data){
            item.petImage?.let {itemAdaptionAdoptBinding.imgItem.loadUrl(it)}
            itemAdaptionAdoptBinding.petBio.text = item.profileBio
            itemAdaptionAdoptBinding.nameItem.text = item.name
            setFavoritePet(item)
        }

        private fun setFavoritePet(itemPet: AdaptionAdoptMeResponse.Data) {
            itemAdaptionAdoptBinding.heartIconId.setEventListener(object : SparkEventListener {
                override fun onEvent(button: ImageView, buttonState: Boolean) {
                    buttonState.apply {
                        itemPet.id?.let {
                            if (userId != null) {
                                pawsViewModel.addPetToFavorite(userId, it)
                            }
                        }
                    }

                }

                override fun onEventAnimationEnd(button: ImageView?, buttonState: Boolean) {

                }
                override fun onEventAnimationStart(button: ImageView?, buttonState: Boolean) {

                }
            })
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