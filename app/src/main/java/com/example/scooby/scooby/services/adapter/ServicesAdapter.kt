package com.example.scooby.scooby.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.services.ServicesResponse
import com.example.scooby.databinding.ServicesItemHomeBinding

class ServicesAdapter(private var servicesList: ServicesResponse) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {
    inner class ServicesViewHolder(private val servicesItemHomeBinding: ServicesItemHomeBinding) :
        RecyclerView.ViewHolder(servicesItemHomeBinding.root) {
        fun bind(service: ServicesResponse.ShuffledService) {
            Glide.with(itemView).load(service.serviceImage)
                .into(servicesItemHomeBinding.serviceImage)
            servicesItemHomeBinding.serviceType.text = service.serviceType
            servicesItemHomeBinding.city.text = service.city
            servicesItemHomeBinding.price.text = service.price.toString()
            servicesItemHomeBinding.rate.rating = service.rate.toFloat()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        return ServicesViewHolder(
            ServicesItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bind(servicesList.shuffledServices[position])
    }

}