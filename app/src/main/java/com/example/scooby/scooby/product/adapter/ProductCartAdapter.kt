package com.example.scooby.scooby.product.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.CartProductResponse
import com.example.scooby.databinding.ItemCartBinding
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.IRefreshListListener
import com.example.scooby.utils.loadUrl

class ProductCartAdapter(
    private val productViewModel: ProductViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val listener : IRefreshListListener
) : ListAdapter<CartProductResponse.Data.CartItem, ProductCartAdapter.ProductCartViewHolder>(
    ProductCartDiffCallBack()
) {
    override fun getItem(position: Int): CartProductResponse.Data.CartItem {
        return super.getItem(position)
    }

    inner class ProductCartViewHolder(private val itemBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private var productCount: Int = 0
        fun bind(productCart: CartProductResponse.Data.CartItem) {
            var quantity = productCart.quantity
            productCount = productCart.quantity!!
            productCart.product.productImage?.let { itemBinding.cartImage.loadUrl(it) }
            itemBinding.itemCartTitle.text = productCart.product.name
            itemBinding.itemCardPrice.text = productCart.price.toString()
            itemBinding.tvPriceAmount.text = productCart.quantity.toString()
            if (productCart.product.discount.toString() == "0") {
                itemBinding.layoutDiscount.visibility = View.GONE

            } else {
                itemBinding.layoutDiscount.visibility = View.VISIBLE
                itemBinding.originalPrice.text = productCart.price.toString()
                itemBinding.offerForProduct.text = productCart.product.discount.toString()
            }

            itemBinding.incrementAmountBtn.setOnClickListener {
                productCount++
                itemBinding.tvPriceAmount.text = productCount.toString()
                productCart.id?.let { it1 -> productViewModel.increaseProductCount(it1) }
            }
            itemBinding.decrementAmountBtn.setOnClickListener {
                if (productCount >= 1) {
                    productCount--
                    productCart.id?.let { it1 -> productViewModel.decreaseProductCount(it1) }
                    itemBinding.tvPriceAmount.text = productCount.toString()
                }
            }
            deleteItemFromCart(productCart)
            observeViews()
            observeViewsIncrease()
            observeViewsDecrease()
        }

        //stile work not finish
        private fun deleteItemFromCart(productCart: CartProductResponse.Data.CartItem) {
            itemBinding.itemCartDelete.setOnClickListener {
                Log.i("cart", "Item removed")
                Toast.makeText(itemView.context, "Deleted", Toast.LENGTH_SHORT).show()
                productCart.product.id?.let { productId ->
                        productViewModel.deleteProductFromCart(productId)
                        Log.i("infoDeletedPro", productId)
                }
            }
        }
    }
    private fun observeViews() {
        productViewModel.deleteProductCartResult.observe(lifecycleOwner) { baseResponse ->
            when (baseResponse) {
                is BaseResponse.Success -> {
                        listener.onRefreshList()
                }

                is BaseResponse.Error -> Log.i("DeleteItem",baseResponse.msg.toString())
                is BaseResponse.Loading -> {}
            }
        }
    }
    private fun observeViewsIncrease() {
        productViewModel.increaseProductCartResult.observe(lifecycleOwner) { baseResponse ->
            when (baseResponse) {
                is BaseResponse.Success -> {
                    listener.onRefreshList()
                }

                is BaseResponse.Error -> Log.i("Increased", baseResponse.msg.toString())
                is BaseResponse.Loading -> {}
            }
        }
    }

    private fun observeViewsDecrease() {
        productViewModel.decreaseProductCartResult.observe(lifecycleOwner) { baseResponse ->
            when (baseResponse) {
                is BaseResponse.Success -> {
                    listener.onRefreshList()
                }

                is BaseResponse.Error -> Log.i("decreased", baseResponse.msg.toString())
                is BaseResponse.Loading -> {}
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

//    override fun getItemCount(): Int = cartResponse.size

    override fun onBindViewHolder(holder: ProductCartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductCartDiffCallBack : DiffUtil.ItemCallback<CartProductResponse.Data.CartItem>() {
    override fun areItemsTheSame(
        oldItem: CartProductResponse.Data.CartItem,
        newItem: CartProductResponse.Data.CartItem
    ): Boolean {
        Log.i("cart", oldItem.product.id + " " + newItem.product.id)
        return oldItem.product.id == newItem.product.id
    }

    override fun areContentsTheSame(
        oldItem: CartProductResponse.Data.CartItem,
        newItem: CartProductResponse.Data.CartItem
    ): Boolean {
        Log.i("cart", oldItem.quantity.toString() + " " + newItem.quantity.toString())
        return oldItem.quantity == newItem.quantity
    }
}


