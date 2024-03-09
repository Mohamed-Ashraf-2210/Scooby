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
import com.example.scooby.scooby.data.model.MyPetsResponse

class MyPetsSideMenuAdapter(private val myPetsList: MyPetsResponse, private val context: Context) :
RecyclerView.Adapter<MyPetsSideMenuAdapter.MyPetsViewHolder>(){
    class MyPetsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val myPetImage: ImageView = itemView.findViewById(R.id.your_pet_icon)
        val myPetName: TextView = itemView.findViewById(R.id.your_pet_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPetsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.your_pets_item_home, parent, false)
        return MyPetsSideMenuAdapter.MyPetsViewHolder(itemView)
    }

    override fun getItemCount() = myPetsList.data.size

    override fun onBindViewHolder(holder: MyPetsViewHolder, position: Int) {
        val currentItem = myPetsList.data
        Glide.with(context).load(currentItem[position].petimage).into(holder.myPetImage)
        holder.myPetName.text = currentItem[position].name
    }
}