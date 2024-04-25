package com.example.scooby.scooby.product.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.scooby.databinding.ItemFavoritePetsBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.utils.loadUrl
import com.varunest.sparkbutton.SparkEventListener

class FavoritePetsAdapter(
    private val petsResponse: AdaptionAdoptMeResponse,
    val pawsViewModel: PawsViewModel,
    private val userId: String?
) :
    RecyclerView.Adapter<FavoritePetsAdapter.FavoritePetsViewHolder>() {
    inner class FavoritePetsViewHolder(private val itemBinding: ItemFavoritePetsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemPetsFavorite: AdaptionAdoptMeResponse.Data) {
            itemPetsFavorite.petImage?.let { itemBinding.imgItem.loadUrl(it) }
            itemBinding.nameItem.text = itemPetsFavorite.name
            itemBinding.petsBio.text = itemPetsFavorite.profileBio
            setFavoritePet(itemPetsFavorite)
        }

        private fun setFavoritePet(itemPet: AdaptionAdoptMeResponse.Data) {
            itemBinding.heartIconId.isChecked = true
            itemBinding.heartIconId.setEventListener(object : SparkEventListener {
                override fun onEvent(button: ImageView, buttonState: Boolean) {
                    buttonState.apply {

                        if (userId != null) {
                            itemPet.id?.let { pawsViewModel.addPetToFavorite(userId, it) }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePetsViewHolder {
        return FavoritePetsViewHolder(
            ItemFavoritePetsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = petsResponse.data.size

    override fun onBindViewHolder(holder: FavoritePetsViewHolder, position: Int) {
        holder.bind(petsResponse.data[position])
    }
}
