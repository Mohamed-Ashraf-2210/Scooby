package com.example.scooby.scooby.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.booking.BookingResponse
import com.example.scooby.databinding.ItemBooking1Binding
import com.example.scooby.databinding.ItemBookingPastBinding
import com.example.scooby.utils.loadUrl

class PastBookAdapter(
    private val upcomingRes: BookingResponse
) : RecyclerView.Adapter<PastBookAdapter.PastBookAdapterViewHolder>() {
    inner class PastBookAdapterViewHolder(
        private val itemBooking1Binding: ItemBookingPastBinding
    ) : RecyclerView.ViewHolder(itemBooking1Binding.root) {
        fun bind(data: BookingResponse.Request) {
            data.serviceImage?.let { itemBooking1Binding.requestImg.loadUrl(it) }
            itemBooking1Binding.requestName.text = data.serviceType
            itemBooking1Binding.numPets.text = data.petsNumber.toString()
            itemBooking1Binding.requestDate.text = data.date.toString()
            itemBooking1Binding.requestDuration.text = data.duration
            itemBooking1Binding.payment.text = data.payment
            if(data.completed != false){
                itemBooking1Binding.tvComplete.text = data.completed.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastBookAdapterViewHolder {
        return PastBookAdapterViewHolder(
            ItemBookingPastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = upcomingRes.request.size

    override fun onBindViewHolder(holder: PastBookAdapterViewHolder, position: Int) {
        holder.bind(upcomingRes.request[position])
    }

}