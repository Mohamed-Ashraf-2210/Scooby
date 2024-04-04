package com.example.scooby.scooby.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.product.ProductResponse
import com.example.scooby.databinding.ItemProductBinding
import com.example.scooby.utils.loadUrl

class ProductAdapter(private val productList : ProductResponse, private val context: Context) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(private val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {
            fun bind(product : ProductResponse.Data){
                product.productImage?.let { itemProductBinding.medImage.loadUrl(it) }
                itemProductBinding.medTitle.text = product.name
                itemProductBinding.medDescription.text = product.desc
                itemProductBinding.medDiscountedPrice.text = product.priceAfterDiscount.toString()
                itemProductBinding.medRealPrice.text = product.price.toString()
                itemProductBinding.medOfferPercent.text = product.discount.toString()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = productList.data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList.data[position])
    }
}