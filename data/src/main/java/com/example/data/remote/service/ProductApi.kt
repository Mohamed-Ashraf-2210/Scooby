package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.product.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/scooby/api/product/getallproduct")
    suspend fun getAllProduct(): Response<ProductResponse>


    companion object{
        fun getApi(): ProductApi? {
            return ApiClient.client?.create(ProductApi::class.java)
        }
    }
}