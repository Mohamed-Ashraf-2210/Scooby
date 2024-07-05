package com.example.scooby.scooby.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.CartProductResponse
import com.example.scooby.databinding.ItemProductPaymentBinding
import com.example.scooby.utils.loadUrl

class CheckoutPaymentAdapter(private val cartRes: CartProductResponse) :
    RecyclerView.Adapter<CheckoutPaymentAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBind: ItemProductPaymentBinding) :
        RecyclerView.ViewHolder(itemBind.root) {
        fun bind(product: CartProductResponse.Data.CartItem) {
            product.product.productImage?.let { itemBind.productImg.loadUrl(it) }
            itemBind.productName.text = product.product.name
            itemBind.prodcutPrice.text = product.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = cartRes.data.cartItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cartRes.data.cartItems[position])
    }
}