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
        Log.d("DiffUtil", "Comparing items: old (${oldList[oldItemPosition].id}), new (${newList[newItemPosition].id})")
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}