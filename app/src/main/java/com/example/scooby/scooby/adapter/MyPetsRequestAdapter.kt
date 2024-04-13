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

class MyPetsRequestAdapter(
    private val myPetsList: MyPetsResponse,
    private val context: Context
) : RecyclerView.Adapter<MyPetsRequestAdapter.MyPetsViewHolder>() {

    private val itemSelected: MutableList<String> = mutableListOf()

    class MyPetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myPetImage: ImageView = itemView.findViewById(R.id.pet_image_my_pets)
        val myPetName: TextView = itemView.findViewById(R.id.name_pet_my_pets)
        val myPetType: TextView = itemView.findViewById(R.id.type_pet_my_pets)
        val checkSelected: ImageView = itemView.findViewById(R.id.check_selected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPetsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_pet_add, parent, false)
        return MyPetsViewHolder(itemView)
    }

    override fun getItemCount() = myPetsList.data.size

    fun getItemSelected() = itemSelected

    override fun onBindViewHolder(holder: MyPetsViewHolder, position: Int) {
        val currentItem = myPetsList.data[position]
        Glide.with(context)
            .load(currentItem.petImage)
            .transform(CenterCrop())
            .into(holder.myPetImage)
        holder.myPetName.text = currentItem.name
        holder.myPetType.text = currentItem.type
        holder.checkSelected.visibility =
            if (currentItem.id in itemSelected) View.VISIBLE else View.GONE

        holder.myPetImage.setOnClickListener {
            val itemId = currentItem.id
            if (itemId in itemSelected) {
                itemSelected.remove(itemId)
                holder.checkSelected.visibility = View.GONE
            } else {
                itemSelected.add(itemId)
                holder.checkSelected.visibility = View.VISIBLE
            }
        }
    }
}