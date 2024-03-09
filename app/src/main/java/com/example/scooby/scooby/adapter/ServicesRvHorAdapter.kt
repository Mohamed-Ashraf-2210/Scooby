package com.example.scooby.scooby.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scooby.databinding.ServicesCircleItemBinding
import com.example.scooby.scooby.data.model.ServicesRvList

class ServicesRvHorAdapter(private val arraylist:ArrayList<ServicesRvList>,private val context: Context) :RecyclerView.Adapter<ServicesRvHorAdapter.ServicesRvHorViewHolder>(){

    class ServicesRvHorViewHolder(binding: ServicesCircleItemBinding): RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = binding.icItemCircle
        val name = binding.nameIcItemCircle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesRvHorViewHolder {
        val binding = ServicesCircleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ServicesRvHorViewHolder(binding)
    }

    override fun getItemCount() = arraylist.size

    override fun onBindViewHolder(holder: ServicesRvHorViewHolder, position: Int) {
        val currentItem = arraylist[position]
        Glide.with(context).load(currentItem.image).into(holder.img)
//        holder.img.setImageDrawable(currentItem.image.toDrawable())
        holder.name.text = currentItem.name
    }
}