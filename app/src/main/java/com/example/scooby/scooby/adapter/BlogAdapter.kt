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
import com.example.domain.blog.BlogResponse

class BlogAdapter(private val blogList: BlogResponse, private val context: Context) :RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {
    class BlogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgLink:ImageView = itemView.findViewById(R.id.img_card_plog)
        val imgTitle:TextView = itemView.findViewById(R.id.title_card_plog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        return BlogViewHolder(itemView)
    }

    override fun getItemCount() = blogList.data.size

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val currentItem = blogList.data
        Glide.with(context).load(currentItem[position].blogImage).into(holder.imgLink)
        holder.imgTitle.text = currentItem[position].description

    }

}