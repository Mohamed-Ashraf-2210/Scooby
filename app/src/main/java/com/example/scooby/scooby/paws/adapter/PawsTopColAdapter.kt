package com.example.scooby.scooby.paws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.scooby.databinding.ItemAdaptionOneBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.utils.loadUrl
import com.varunest.sparkbutton.SparkEventListener

class PawsTopColAdapter(
    private val adaptionTopResponse: AdaptionResponse,
    val pawsViewModel: PawsViewModel,
) : RecyclerView.Adapter<PawsTopColAdapter.PawsTopColViewHolder>() {
    inner class PawsTopColViewHolder(private val itemAdaptionOneBinding: ItemAdaptionOneBinding) :
        RecyclerView.ViewHolder(itemAdaptionOneBinding.root) {
            fun bind(item : AdaptionResponse.Data){
                item.petImage?.let { itemAdaptionOneBinding.imgItem.loadUrl(it) }
                itemAdaptionOneBinding.nameItem.text = item.name
                itemAdaptionOneBinding.titleItem.text = item.category
                setFavoritePet(item)
            }

        private fun setFavoritePet(itemPet: AdaptionResponse.Data) {
            itemAdaptionOneBinding.heartIconId.setEventListener(object : SparkEventListener {
                override fun onEvent(button: ImageView, buttonState: Boolean) {
                    buttonState.apply {
                            itemPet.id?.let { pawsViewModel.addPetToFavorite(it) }
                    }

                }

                override fun onEventAnimationEnd(button: ImageView?, buttonState: Boolean) {

                }

                override fun onEventAnimationStart(button: ImageView?, buttonState: Boolean) {

                }
            })
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