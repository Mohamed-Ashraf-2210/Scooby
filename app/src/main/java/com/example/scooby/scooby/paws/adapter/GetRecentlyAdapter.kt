package com.example.scooby.scooby.paws.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.missing.CatsResponse
import com.example.domain.paws.missing.GetRecentlyResponse
import com.example.scooby.databinding.ItemPetMissingBinding
import com.example.scooby.utils.loadUrl

class GetRecentlyAdapter(private val recentlyRes: GetRecentlyResponse) :
    RecyclerView.Adapter<GetRecentlyAdapter.GetRecentlyViewHolder>() {
    inner class GetRecentlyViewHolder(private val itemBinding: ItemPetMissingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item : GetRecentlyResponse.RecentlyAddedPet) {
            item.petImage?.let {
                itemBinding.petImage.loadUrl(it)
            }
            itemBinding.petDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetRecentlyViewHolder {
        return GetRecentlyViewHolder(
            ItemPetMissingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = recentlyRes.recentlyAddedPets.size

    override fun onBindViewHolder(holder: GetRecentlyViewHolder, position: Int) {
        holder.bind(recentlyRes.recentlyAddedPets[position])
    }
}