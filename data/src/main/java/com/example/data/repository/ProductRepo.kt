package com.example.data.repository

import com.example.data.remote.service.ProductApi
import com.example.domain.product.ProductResponse
import retrofit2.Response

class ProductRepo {
    suspend fun getAllProduct():Response<ProductResponse>?{
        return ProductApi.getApi()?.getAllProduct()
    }
}