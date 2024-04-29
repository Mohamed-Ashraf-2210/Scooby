package com.example.scooby.scooby.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.ServicesProfileResponse
import com.example.scooby.databinding.ItemReviewBinding
import com.example.scooby.utils.loadUrl

class ReviewAdapter(
    private val reviews: List<ServicesProfileResponse.Data.ReviewsOfService?>
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    lateinit var allReviews: ServicesProfileResponse.Data.ReviewsOfService

    inner class ReviewViewHolder(private val itemReviewBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemReviewBinding.root) {
        fun bind(item: ServicesProfileResponse.Data.ReviewsOfService) {
            item.user?.profileImage?.let { itemReviewBinding.civProfile.loadUrl(it) }
            itemReviewBinding.tvName.text = item.name.toString()
            itemReviewBinding.rate.rating = item.rating?.toFloat()!!
            itemReviewBinding.tvReview.text = item.review.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ItemReviewBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        reviews[position]?.let { holder.bind(it) }
    }
}