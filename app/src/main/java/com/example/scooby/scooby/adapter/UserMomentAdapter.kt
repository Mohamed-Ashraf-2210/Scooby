package com.example.scooby.scooby.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.UserMomentResponse
import com.example.scooby.databinding.ItemPostBinding
import com.example.scooby.utils.loadUrl

class UserMomentAdapter(private val momentRes : List<UserMomentResponse.ProcessedPost.Post>) : RecyclerView.Adapter<UserMomentAdapter.UserViewHolder>() {
    inner class UserViewHolder(
        private val itemBind: ItemPostBinding
    ) : RecyclerView.ViewHolder(itemBind.root) {
        fun bind(data : UserMomentResponse.ProcessedPost.Post){
            data.postImage?.let { itemBind.userPostImage.loadUrl(it) }
            itemBind.userPostName.text = data.userName
            data.userImage?.let { itemBind.userPostImage.loadUrl(it) }
            itemBind.descriptionPost.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount() = momentRes.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(momentRes[position])
    }
}