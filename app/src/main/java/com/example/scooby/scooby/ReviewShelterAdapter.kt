package com.example.scooby.scooby

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.ShelterProfileResponse
import com.example.scooby.databinding.ItemReviewBinding
import com.example.scooby.utils.loadUrl

class ReviewShelterAdapter(
    private val reviews: List<ShelterProfileResponse.ReviewsOfShelter?>
) : RecyclerView.Adapter<ReviewShelterAdapter.ReviewShelterViewHolder>() {

    inner class ReviewShelterViewHolder(private val itemReviewBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemReviewBinding.root) {
        fun bind(item: ShelterProfileResponse.ReviewsOfShelter) {
            item.user?.profileImage?.let { itemReviewBinding.civProfile.loadUrl(it) }
            itemReviewBinding.tvName.text = item.name.toString()
            itemReviewBinding.rate.rating = item.rating?.toFloat()!!
            itemReviewBinding.tvReview.text = item.review.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewShelterViewHolder {
        return ReviewShelterViewHolder(
            ItemReviewBinding.inflate(LayoutInflater
                .from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewShelterViewHolder, position: Int) {
        reviews[position]?.let { holder.bind(it) }
    }
}