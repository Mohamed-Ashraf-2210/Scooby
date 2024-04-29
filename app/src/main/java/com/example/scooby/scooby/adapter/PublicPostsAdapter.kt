package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.community.PublicPosts
import com.example.scooby.R
import com.varunest.sparkbutton.SparkButton

class PublicPostsAdapter(private val postsList: PublicPosts, private val context: Context) :
    RecyclerView.Adapter<PublicPostsAdapter.PublicPostViewHolder>() {

    class PublicPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userPostImage: ImageView = itemView.findViewById(R.id.user_post_image)
        val userName: TextView = itemView.findViewById(R.id.user_post_name)
        val timePost: TextView = itemView.findViewById(R.id.time_post)
        val descriptionPost: TextView = itemView.findViewById(R.id.description_post)
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        val loveIcon: SparkButton = itemView.findViewById(R.id.love_icon)
        val loveText: TextView = itemView.findViewById(R.id.love_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicPostViewHolder {
        return PublicPostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun getItemCount() = postsList.shuffledPosts.size

    override fun onBindViewHolder(holder: PublicPostViewHolder, position: Int) {
        val currentItem = postsList.shuffledPosts[position]
        Glide.with(context).load(currentItem.userImage).into(holder.userPostImage)
        holder.userName.text = currentItem.userName
        holder.descriptionPost.text = currentItem.description
        holder.loveText.text = currentItem.likesNumber.toString()

    }
}