package com.example.scooby.scooby.product.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.product.ProductResponse

class ProductDiffUtilCallBack(
    private val oldList: List<ProductResponse.Data>,
    private val newList: List<ProductResponse.Data>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.d("DiffUtil", "Comparing items: old (${oldItem.id}), new (${newItem.id})")
        return oldList[oldItemPosition].id == newList[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val areContentTheSame = when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }

            oldList[oldItemPosition].desc != newList[newItemPosition].desc -> {
                false
            }

            oldList[oldItemPosition].price != newList[newItemPosition].price -> {
                false
            }

            oldList[oldItemPosition].priceAfterDiscount != newList[newItemPosition].priceAfterDiscount -> {
                false
            }

            oldList[oldItemPosition].productImage != newList[newItemPosition].productImage -> {
                false
            }

            oldList[oldItemPosition].discount != newList[newItemPosition].discount -> {
                false
            }
            else -> true

        }
        Log.d("DiffUtil", "Contents same: $areContentTheSame")
        return  areContentTheSame
    }
}