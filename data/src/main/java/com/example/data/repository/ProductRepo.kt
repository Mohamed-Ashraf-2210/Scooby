package com.example.data.repository

import com.example.data.remote.service.ProductApi
import com.example.domain.AddFavoriteResponse
import com.example.domain.product.ProductResponse
import retrofit2.Response


class ProductRepo {
    suspend fun getAllProduct():Response<ProductResponse>?{
        return ProductApi.getApi()?.getAllProduct()
    }

    suspend fun getFavoriteProduct(userId : String) :Response<ProductResponse>?{
        return ProductApi.getApi()?.getFavoriteProduct(userId)
    }

    suspend fun addProductToFavorites(userId: String,productId: String):Response<AddFavoriteResponse>?{
        return ProductApi.getApi()?.addProductToFavorite(userId,productId)
    }


}