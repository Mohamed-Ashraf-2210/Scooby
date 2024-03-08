package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scooby.R
import com.example.scooby.scooby.data.model.ServicesResponse

class ServicesAdapter(private var servicesList: ServicesResponse,private val context: Context) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    class ServicesViewHolder(iteView: View): RecyclerView.ViewHolder(iteView) {

        val serviceImage: ImageView = iteView.findViewById(R.id.service_image)
        val serviceType: TextView = iteView.findViewById(R.id.service_type)
        val city: TextView = iteView.findViewById(R.id.city)
        val price: TextView = iteView.findViewById(R.id.price)
        val rate: RatingBar = iteView.findViewById(R.id.rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.services_item_home, parent, false)
        return ServicesViewHolder(itemView)
    }

    override fun getItemCount() = servicesList.allServices.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {

        val currentItem = servicesList.allServices
        Glide.with(context).load(currentItem[position].serviceImage).into(holder.serviceImage)
        holder.serviceType.text = currentItem[position].serviceType
        holder.city.text = currentItem[position].city
        holder.price.text = currentItem[position].price.toString()
        holder.rate.rating = currentItem[position].rate.toFloat()
    }

}