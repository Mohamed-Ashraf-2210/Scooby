package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.blog.BlogResponse
import com.example.scooby.databinding.BlogsItemHomeBinding
import com.example.scooby.utils.loadUrl

class BlogHomeAdapter(private val blogList: BlogResponse) :
    RecyclerView.Adapter<BlogHomeAdapter.BlogHomeViewHolder>() {
    inner class BlogHomeViewHolder(private val itemHomeBinding: BlogsItemHomeBinding) :
        RecyclerView.ViewHolder(itemHomeBinding.root) {
        fun bind(blog: BlogResponse.Data) {
            blog.plogImage.let { itemHomeBinding.imgCardBlogHome.loadUrl(it) }
            itemHomeBinding.titleCardBlogHome.text = blog.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHomeViewHolder {
        return BlogHomeViewHolder(
            BlogsItemHomeBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = blogList.data.size


    override fun onBindViewHolder(holder: BlogHomeViewHolder, position: Int) {
        holder.bind(blogList.data[position])
    }
}