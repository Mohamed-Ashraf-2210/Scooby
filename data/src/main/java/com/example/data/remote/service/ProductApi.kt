package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.PostString
import com.example.domain.product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @GET("/scooby/api/product/getallproduct")
    suspend fun getAllProduct(): Response<ProductResponse>

    @POST("/scooby/api/fav/addfav/{id}/productId")
    suspend fun addProductToFavorite(
        @Query("id")
        id :String,
        @Body
        productId : PostString
    )


    companion object{
        fun getApi(): ProductApi? {
            return ApiClient.client?.create(ProductApi::class.java)
        }
    }
}