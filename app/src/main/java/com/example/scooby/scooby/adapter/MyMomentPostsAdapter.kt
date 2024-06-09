package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.domain.community.MyMomentsPosts
import com.example.scooby.R
import com.example.scooby.scooby.viewmodel.CommunityViewModel
import com.varunest.sparkbutton.SparkButton
import com.varunest.sparkbutton.SparkEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class MyMomentPostsAdapter(
    private val communityViewModel: CommunityViewModel,
    private val postsList: MyMomentsPosts,
    private val context: Context
) :
    RecyclerView.Adapter<MyMomentPostsAdapter.MyMomentPostViewHolder>() {

    class MyMomentPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userPostImage: ImageView = itemView.findViewById(R.id.user_post_image)
        val userName: TextView = itemView.findViewById(R.id.user_post_name)
        val timePost: TextView = itemView.findViewById(R.id.time_post)
        val descriptionPost: TextView = itemView.findViewById(R.id.description_post)
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        val loveIcon: SparkButton = itemView.findViewById(R.id.love_icon)
        val loveText: TextView = itemView.findViewById(R.id.love_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMomentPostViewHolder {
        return MyMomentPostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun getItemCount() = postsList.processedPosts.size

    override fun onBindViewHolder(holder: MyMomentPostViewHolder, position: Int) {
        val currentItem = postsList.processedPosts[position].post
        Glide.with(context).load(currentItem.userImage).transform(CenterCrop())
            .into(holder.userPostImage)
        holder.userName.text = currentItem.userName
        holder.descriptionPost.text = currentItem.description
        holder.timePost.text = getTimePost(currentItem.createdAt)
        holder.loveText.text = currentItem.likesNumber.toString()
        holder.loveIcon.isChecked = postsList.processedPosts[position].liked
        Glide.with(context).load(currentItem.postImage).transform(CenterCrop())
            .into(holder.postImage)
        holder.loveIcon.setEventListener(object : SparkEventListener {
            override fun onEvent(button: ImageView?, buttonState: Boolean) {
                communityViewModel.apply {
                    likePost(currentItem.id)
//                    if (holder.loveIcon.isChecked) {
//                        holder.loveText.text = (currentItem.likesNumber + 1).toString()
//                    } else {
//                        holder.loveText.text = (currentItem.likesNumber - 1).toString()
//                    }
                }
            }

            override fun onEventAnimationEnd(button: ImageView?, buttonState: Boolean) {
            }

            override fun onEventAnimationStart(button: ImageView?, buttonState: Boolean) {
            }

        })
    }

    private fun getTimePost(createdAt: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val timesNow = dateFormat.format(Calendar.getInstance().time)

        val date1 = dateFormat.parse(createdAt)
        val date2 = dateFormat.parse(timesNow)

        val difference = date2!!.time - date1!!.time

        val seconds = difference / 1000 % 60
        val minutes = difference / (1000 * 60) % 60
        val hours = difference / (1000 * 60 * 60) % 24
        val days = difference / (1000 * 60 * 60 * 24)

        return when {
            days > 0 -> {
                "$days d"
            }

            hours > 0 -> {
                "$hours h"
            }

            minutes > 0 -> {
                "$minutes m"
            }

            else -> {
                "$seconds sec"
            }
        }
    }

}