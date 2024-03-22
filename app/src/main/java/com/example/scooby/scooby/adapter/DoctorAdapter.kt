package com.example.scooby.scooby.adapter

import android.annotation.SuppressLint
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
import com.example.scooby.scooby.data.model.DoctorsResponse

class DoctorAdapter(private var doctorList: DoctorsResponse, private val context: Context) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorImage: ImageView = itemView.findViewById(R.id.image_doctor_vet)
        val doctorName: TextView = itemView.findViewById(R.id.name_doctor_tv)
        val doctorRate: TextView = itemView.findViewById(R.id.rate_doctor_tv)
        val doctorRateBar: RatingBar = itemView.findViewById(R.id.rate_bar_veterinary)
        val doctorNumberOfRate: TextView = itemView.findViewById(R.id.number_of_rate_doctor_tv)
        val doctorDescription: TextView = itemView.findViewById(R.id.description_doctor_tv)
        val doctorReview: TextView = itemView.findViewById(R.id.review_doctor_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.doctors_item, parent, false)
        return DoctorViewHolder(itemView)
    }

    override fun getItemCount() = doctorList.data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val currentItem = doctorList.data
        Glide.with(context).load(currentItem[position].doctorImage).into(holder.doctorImage)
        holder.doctorName.text = currentItem[position].name
        holder.doctorRate.text = currentItem[position].rate.toString()
        holder.doctorRateBar.rating = currentItem[position].rate.toFloat()
        holder.doctorNumberOfRate.text = "(${currentItem[position].numberOfRate})"
        holder.doctorDescription.text = currentItem[position].description
        holder.doctorReview.text = currentItem[position].review
    }
}