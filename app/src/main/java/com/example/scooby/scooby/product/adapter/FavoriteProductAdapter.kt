package com.example.scooby.scooby.product.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.product.ProductResponse
import com.example.scooby.databinding.ItemProductFavoriteBinding
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.loadUrl
import com.varunest.sparkbutton.SparkEventListener

class FavoriteProductAdapter(
    private val productList: ProductResponse,
    val productViewModel: ProductViewModel,
    private val userId: String?
) :
    RecyclerView.Adapter<FavoriteProductAdapter.FavoriteProductViewHolder>() {
    inner class FavoriteProductViewHolder(private val itemFav: ItemProductFavoriteBinding) :
        RecyclerView.ViewHolder(itemFav.root) {
        fun bind(itemProductFav: ProductResponse.Data) {
            itemProductFav.productImage?.let { itemFav.medImage.loadUrl(it) }
            itemFav.medTitle.text = itemProductFav.name
            itemFav.medDescription.text = itemProductFav.desc
            itemFav.medDiscountedPrice.text = itemProductFav.priceAfterDiscount.toString()
            itemFav.medRealPrice.text = itemProductFav.price.toString()
            itemFav.medOfferPercent.text = itemProductFav.discount.toString()
            setFavoriteProduct(itemProductFav)

        }

        private fun setFavoriteProduct(product: ProductResponse.Data) {
            itemFav.heartIconId.isChecked = true
            itemFav.heartIconId.setEventListener(object : SparkEventListener {
                override fun onEvent(button: ImageView, buttonState: Boolean) {
                    buttonState.apply {
                        product.id?.let {
                            if (userId != null) {
                                productViewModel.addProductToFavorite(userId, it)
                            }
                        }
                    }

                }

                override fun onEventAnimationEnd(button: ImageView?, buttonState: Boolean) {

                }

                override fun onEventAnimationStart(button: ImageView?, buttonState: Boolean) {

                }
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder {
        return FavoriteProductViewHolder(
            ItemProductFavoriteBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = productList.data.size

    override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
        holder.bind(productList.data[position])
    }
}
