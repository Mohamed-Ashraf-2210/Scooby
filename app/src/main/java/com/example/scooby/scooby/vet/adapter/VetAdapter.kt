package com.example.scooby.scooby.vet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.vet.VetResponse
import com.example.scooby.R

class VetAdapter(private var vetList: VetResponse, private val context: Context) :
    RecyclerView.Adapter<VetAdapter.VetViewHolder>() {

    class VetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val veterinaryImage: ImageView = itemView.findViewById(R.id.image_veterinary)
        val veterinaryName: TextView = itemView.findViewById(R.id.veterinary_name_tv)
        val veterinaryRate: TextView = itemView.findViewById(R.id.rate_veterinary_tv)
        val veterinaryRateBar: RatingBar = itemView.findViewById(R.id.rate_bar_veterinary)
        val veterinaryNumberOfRate: TextView =
            itemView.findViewById(R.id.number_of_rate_veterinary_tv)
        val veterinaryDescription: TextView = itemView.findViewById(R.id.description_veterinary_tv)
        val veterinaryReview: TextView = itemView.findViewById(R.id.review_veterinary_tv)
        val callBtn: Button = itemView.findViewById(R.id.call_pharmacy_btn)
        val locationBtn: Button = itemView.findViewById(R.id.location_pharmacy_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.veterinary_nearby_item, parent, false)
        return VetViewHolder(itemView)
    }

    override fun getItemCount() = vetList.data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VetViewHolder, position: Int) {
        val currentItem = vetList.data
        Glide.with(context).load(currentItem[position].vetImage).into(holder.veterinaryImage)
        holder.veterinaryName.text = currentItem[position].vetName
        holder.veterinaryRate.text = currentItem[position].rate.toString()
        holder.veterinaryRateBar.rating = currentItem[position].rate.toFloat()
        holder.veterinaryNumberOfRate.text = "(${currentItem[position].numberOfRate})"
        holder.veterinaryDescription.text = currentItem[position].bio
        holder.veterinaryReview.text = currentItem[position].review
        holder.callBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentItem[position].callNumber}")
            }
            startActivity(context, intent, Bundle())
        }

    }
}