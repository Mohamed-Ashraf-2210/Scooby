package com.example.scooby.scooby.userProfile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.domain.profile.ProfileDetailsResponse
import com.example.scooby.R
import com.example.scooby.scooby.userProfile.fragment.ProfileFragmentDirections

class MyPetsAdapter(private val myPetsList: ProfileDetailsResponse, private val context: Context) :
    RecyclerView.Adapter<MyPetsAdapter.MyPetsViewHolder>() {
    class MyPetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myPetImage: ImageView = itemView.findViewById(R.id.your_pet_icon)
        val myPetName: TextView = itemView.findViewById(R.id.your_pet_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPetsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.your_pets_item_home, parent, false)
        return MyPetsViewHolder(itemView)
    }

    override fun getItemCount() = myPetsList.data.data.pets.size

    override fun onBindViewHolder(holder: MyPetsViewHolder, position: Int) {
        val currentItem = myPetsList.data.data.pets
        Glide.with(context).load(currentItem[position].petImage).transform(CenterCrop())
            .into(holder.myPetImage)
        holder.myPetName.text = currentItem[position].name
        holder.itemView.setOnClickListener {
            val action =
                ProfileFragmentDirections.actionProfileFragmentToPetProfileFragment(position)
            it.findNavController().navigate(action)
        }
    }
}