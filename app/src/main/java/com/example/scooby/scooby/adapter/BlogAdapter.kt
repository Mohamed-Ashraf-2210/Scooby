package com.example.scooby.scooby.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.blog.BlogResponse
import com.example.scooby.databinding.BlogItemBinding
import com.example.scooby.utils.loadUrl

class BlogAdapter(private val blogList: BlogResponse,val context: Context) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {
    inner class BlogViewHolder(private val itemHomeBinding: BlogItemBinding) :
        RecyclerView.ViewHolder(itemHomeBinding.root) {
        fun bind(blog: BlogResponse.Data) {
            blog.plogImage.let { itemHomeBinding.imgCardPlog.loadUrl(it) }
            itemHomeBinding.titleCardPlog.text = blog.description
            itemHomeBinding.imgCardPlog.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(blog.link)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            BlogItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = blogList.data.size


    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(blogList.data[position])
    }
}