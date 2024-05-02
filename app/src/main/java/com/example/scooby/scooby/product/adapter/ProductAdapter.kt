package com.example.scooby.scooby.product.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.product.ProductResponse
import com.example.scooby.databinding.ItemProductBinding
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.loadUrl
import com.varunest.sparkbutton.SparkEventListener

class ProductAdapter(
    private val productViewModel: ProductViewModel,
    val userId: String?,
    private val productList: ProductResponse,
    val favoriteProducts: ProductResponse,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var oldProductList  = emptyList<ProductResponse.Data>()
    inner class ProductViewHolder(private val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {
            fun bind(product : ProductResponse.Data){
                product.productImage?.let { itemProductBinding.medImage.loadUrl(it) }
                itemProductBinding.medTitle.text = product.name
                itemProductBinding.medDescription.text = product.desc
                itemProductBinding.medDiscountedPrice.text = product.priceAfterDiscount.toString()
                itemProductBinding.medRealPrice.text = product.price.toString()
                itemProductBinding.medOfferPercent.text = product.discount.toString()
                if (favoriteProducts.data.contains(product)) {
                    itemProductBinding.heartIconId.isChecked = true
                }
                // itemProductBinding.heartIconId.isChecked = favoriteProducts.data.contains(product)
                setFavoriteProduct(product)
            }

        private fun setFavoriteProduct(product: ProductResponse.Data){

            itemProductBinding.heartIconId.setEventListener(object : SparkEventListener {
                override fun onEvent(button: ImageView, buttonState: Boolean) {
                    if (buttonState) {
                        Toast.makeText(itemView.context,"Added to favorite", Toast.LENGTH_SHORT).show()
                        // Button is
                        if (userId != null) {
                            product.id?.let { productViewModel.addProductToFavorite(userId, it) }
                        }

                    } else {
                        Toast.makeText(itemView.context,"removed from favorite", Toast.LENGTH_SHORT).show()
                        // Button is inactive
                        product.id?.let {
                            if (userId != null) {
                                productViewModel.addProductToFavorite(userId, it)
                            }
                        }
                    }
                }

                override fun onEventAnimationEnd(button: ImageView?, buttonState: Boolean) {}
                override fun onEventAnimationStart(button: ImageView?, buttonState: Boolean) {}
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = productList.data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList.data[position])
    }

    fun setData(newProductList : List<ProductResponse.Data>){
        val diffUtil = ProductDiffUtilCallBack(oldProductList,newProductList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldProductList = newProductList
        diffResult.dispatchUpdatesTo(this)
    }
}