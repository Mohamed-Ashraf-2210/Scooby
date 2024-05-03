package com.example.scooby.scooby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.DoctorProfileResponse
import com.example.scooby.databinding.ItemReviewBinding
import com.example.scooby.utils.loadUrl

class ReviewDoctorAdapter(private val reviews: List<DoctorProfileResponse.Data.ReviewsOfDoctor?>) :
    RecyclerView.Adapter<ReviewDoctorAdapter.ReviewDoctorViewHolder>() {
    inner class ReviewDoctorViewHolder(private val itemReviewBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemReviewBinding.root) {
        fun bind(item: DoctorProfileResponse.Data.ReviewsOfDoctor) {
            item.user?.profileImage?.let { itemReviewBinding.civProfile.loadUrl(it) }
            itemReviewBinding.tvName.text = item.name.toString()
            itemReviewBinding.rate.rating = item.rating?.toFloat()!!
            itemReviewBinding.tvReview.text = item.review.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewDoctorViewHolder {
        return ReviewDoctorViewHolder(
            ItemReviewBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ReviewDoctorViewHolder, position: Int) {
        reviews[position]?.let { holder.bind(it) }
    }
}