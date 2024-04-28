package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.community.MyMomentsPosts
import com.example.scooby.R

class MyMomentPostsAdapter(private val postsList: MyMomentsPosts, private val context: Context) :
    RecyclerView.Adapter<MyMomentPostsAdapter.MyMomentPostViewHolder>() {

    class MyMomentPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userPostImage: ImageView = itemView.findViewById(R.id.user_post_image)
        val userName: TextView = itemView.findViewById(R.id.user_post_name)
        val timePost: TextView = itemView.findViewById(R.id.time_post)
        val descriptionPost: TextView = itemView.findViewById(R.id.description_post)
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        val loveIcon: ImageView = itemView.findViewById(R.id.love_icon)
        val loveText: TextView = itemView.findViewById(R.id.love_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMomentPostViewHolder {
        return MyMomentPostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun getItemCount() = postsList.myMoments.size

    override fun onBindViewHolder(holder: MyMomentPostViewHolder, position: Int) {
        val currentItem = postsList.myMoments[position]
        Glide.with(context).load(currentItem.userImage).into(holder.userPostImage)
        holder.userName.text = currentItem.userName
        holder.descriptionPost.text = currentItem.description
        holder.loveText.text = currentItem.likesNumber.toString()
    }

}