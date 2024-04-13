package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.domain.pet.MyPetsResponse
import com.example.scooby.R

class MyPetsAddAdapter(private val myPetsList: MyPetsResponse, private val context: Context) :
    RecyclerView.Adapter<MyPetsAddAdapter.MyPetsViewHolder>() {
    class MyPetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myPetImage: ImageView = itemView.findViewById(R.id.pet_image_my_pets)
        val myPetName: TextView = itemView.findViewById(R.id.name_pet_my_pets)
        val myPetType: TextView = itemView.findViewById(R.id.type_pet_my_pets)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPetsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_pet_add, parent, false)
        return MyPetsViewHolder(itemView)
    }

    override fun getItemCount() = myPetsList.data.size

    override fun onBindViewHolder(holder: MyPetsViewHolder, position: Int) {
        val currentItem = myPetsList.data
        Glide.with(context).load(currentItem[position].petImage).transform(CenterCrop())
            .into(holder.myPetImage)
        holder.myPetName.text = currentItem[position].name
        holder.myPetType.text = currentItem[position].type
    }
}