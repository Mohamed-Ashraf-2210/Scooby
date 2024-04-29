package com.example.scooby.scooby.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.services.ServicesResponse
import com.example.scooby.databinding.ItemServicesBinding
import com.example.scooby.scooby.services.fragment.ServiceFragmentDirections

class ServicesMainAdapter(private var servicesList: ServicesResponse, private val context: Context) :
    RecyclerView.Adapter<ServicesMainAdapter.ServicesViewHolder>() {

    inner class ServicesViewHolder(private val itemServicesBinding: ItemServicesBinding) :
        RecyclerView.ViewHolder(itemServicesBinding.root) {
        fun bind(service: ServicesResponse.AllService) {
            Glide.with(itemView).load(service.serviceImage).into(itemServicesBinding.serviceImage)
            itemServicesBinding.serviceType.text = service.serviceType
            itemServicesBinding.city.text = service.city
            itemServicesBinding.price.text = service.price.toString()
            itemServicesBinding.rate.rating = service.rate.toFloat()
            navigate2ServiceProfile(service)
        }

        private fun navigate2ServiceProfile(service: ServicesResponse.AllService){
            itemServicesBinding.serviceImage.setOnClickListener {
                val action = ServiceFragmentDirections.actionServicesFragmentToServicesProfileFragment(service.serviceProfile)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        return ServicesViewHolder(
            ItemServicesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    override fun getItemCount() = servicesList.allServices.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bind(servicesList.allServices[position])
    }

}