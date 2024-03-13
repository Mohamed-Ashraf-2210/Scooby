package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scooby.R
import com.example.scooby.scooby.data.model.AllPetsResponse

class PetsHomeAdapter(private val petsList: AllPetsResponse, private val context: Context) :
    RecyclerView.Adapter<PetsHomeAdapter.PetsHomeViewHolder>() {

    class PetsHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petImage: ImageView = itemView.findViewById(R.id.pet_home_image)
        val petName: TextView = itemView.findViewById(R.id.pet_home_name)
        val ownerPetName: TextView = itemView.findViewById(R.id.pet_home_owner_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsHomeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.pets_item_home, parent, false)
        return PetsHomeViewHolder(itemView)
    }

    override fun getItemCount() = petsList.data.size

    override fun onBindViewHolder(holder: PetsHomeViewHolder, position: Int) {
        val currentItem = petsList.data
        Glide.with(context).load(currentItem[position].petImage).into(holder.petImage)
        holder.petName.text = currentItem[position].name
        holder.ownerPetName.text = currentItem[position].owner
    }
}