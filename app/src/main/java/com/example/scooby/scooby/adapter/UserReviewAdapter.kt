package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.UserReviewResponse
import com.example.scooby.databinding.ItemReviewBinding
import com.example.scooby.utils.loadUrl

class UserReviewAdapter(private val reviewRes : UserReviewResponse) : RecyclerView.Adapter<UserReviewAdapter.UserViewHolder>() {


    inner class UserViewHolder(
        private val itemBind: ItemReviewBinding
    ) : RecyclerView.ViewHolder(itemBind.root){
        fun bind(data : UserReviewResponse.Data.Review){
            itemBind.civProfile.loadUrl(data.user?.profileImage.toString())
            itemBind.tvName.text = data.name
            itemBind.rate.rating = data.rating?.toFloat()!!
            itemBind.tvReview.text = data.review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = reviewRes.data.reviews.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(reviewRes.data.reviews[position])
    }
}