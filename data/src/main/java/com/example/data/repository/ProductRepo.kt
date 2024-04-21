package com.example.data.repository

import com.example.data.remote.service.ProductApi
import com.example.domain.product.ProductResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

class ProductRepo {
    suspend fun getAllProduct():Response<ProductResponse>?{
        return ProductApi.getApi()?.getAllProduct()
    }




}