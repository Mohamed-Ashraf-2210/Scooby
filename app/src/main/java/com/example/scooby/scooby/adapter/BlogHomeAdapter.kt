package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scooby.R
import com.example.scooby.scooby.data.model.BlogResponse

class BlogHomeAdapter(private val blogList: BlogResponse, private val context: Context) :
    RecyclerView.Adapter<BlogHomeAdapter.BlogHomeViewHolder>() {
    class BlogHomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgLink: ImageView = itemView.findViewById(R.id.img_card_blog_home)
        val imgTitle: TextView = itemView.findViewById(R.id.title_card_blog_home)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.blogs_item_home, parent, false)
        return BlogHomeViewHolder(itemView)
    }

    override fun getItemCount() = blogList.data.size


    override fun onBindViewHolder(holder: BlogHomeViewHolder, position: Int) {
        val currentItem = blogList.data
        Glide.with(context).load(currentItem[position].plogImage).into(holder.imgLink)
        holder.imgTitle.text = currentItem[position].discription
    }

}