package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.scooby.data.model.ServicesResponse

class ServicesAdapter(private var servicesList: List<ServicesResponse>) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    class ServicesViewHolder(iteView: View): RecyclerView.ViewHolder(iteView) {
        val serviceImage: ImageView = iteView.findViewById(R.id.service_image)
        val serviceType: TextView = iteView.findViewById(R.id.service_type)
        val city: TextView = iteView.findViewById(R.id.city)
        val price: TextView = iteView.findViewById(R.id.price)
        val rate: RatingBar = iteView.findViewById(R.id.rate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val iteView = LayoutInflater.from(parent.context).inflate(R.layout.services_item_home, parent, false)
        return ServicesViewHolder(iteView)
    }

    override fun getItemCount() = servicesList.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val currentItem = servicesList[position]
        holder.serviceImage.setImageResource(currentItem.allServices[position].serviceImage.toInt())
        holder.serviceType.text = currentItem.allServices[position].serviceType
        holder.city.text = currentItem.allServices[position].city
        holder.price.text = currentItem.allServices[position].price.toString()
        holder.rate.rating = currentItem.allServices[position].rate.toFloat()
    }

}