package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.AddFavoriteResponse
import com.example.domain.PostString
import com.example.domain.product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/scooby/api/product/getallproduct")
    suspend fun getAllProduct(): Response<ProductResponse>

//    @POST("/scooby/api/fav/addfav/{id}/productId")
//    suspend fun addProductToFavorite(
//        @Query("id") id: String,
//        @Body
//        productId : PostString
//    )

        @POST("/scooby/api/fav/addfav/{userId}}")
        suspend fun addToProductToFav(
            @Path("userId") id: String,
            @Query("productId") productId: String
        ) :Response<AddFavoriteResponse>




    companion object{
        fun getApi(): ProductApi? {
            return ApiClient.client?.create(ProductApi::class.java)
        }
    }
}