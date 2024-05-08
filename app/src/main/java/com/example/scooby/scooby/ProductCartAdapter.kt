package com.example.scooby.scooby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.CartProductResponse
import com.example.scooby.databinding.ItemCartBinding
import com.example.scooby.utils.loadUrl

class ProductCartAdapter(

    private val cartResponse: List<CartProductResponse.Data.CartItem>,
    private val userId: String?
) : RecyclerView.Adapter<ProductCartAdapter.ProductCartViewHolder>() {
    inner class ProductCartViewHolder(private val itemBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(productCart: CartProductResponse.Data.CartItem) {
            productCart.product.productImage?.let { itemBinding.cartImage.loadUrl(it) }
            itemBinding.itemCartTitle.text = productCart.product.name
            itemBinding.itemCardPrice.text = productCart.price.toString()
            if (productCart.product.discount != null) {
                itemBinding.layoutDiscount.visibility = View.VISIBLE
                itemBinding.originalPrice.text = productCart.price.toString()
                itemBinding.offerForProduct.text = productCart.product.discount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCartViewHolder {
        return ProductCartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cartResponse.size

    override fun onBindViewHolder(holder: ProductCartViewHolder, position: Int) {
        holder.bind(cartResponse[position])
    }
}


