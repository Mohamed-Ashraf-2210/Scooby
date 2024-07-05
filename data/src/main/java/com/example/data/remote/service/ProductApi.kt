package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.AddFavoriteResponse
import com.example.domain.CartProductResponse
import com.example.domain.ProductPatch
import com.example.domain.product.PatchCoupon
import com.example.domain.product.PatchCouponRes
import com.example.domain.product.PatchIncreaseProduct
import com.example.domain.product.ProductResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/scooby/api/product/getallproduct")
    suspend fun getAllProduct(): Response<ProductResponse>


    @GET("/scooby/api/fav/getfavproduct")
    suspend fun getFavoriteProduct(): Response<ProductResponse>

    @PATCH("/scooby/api/fav/addfav")
    suspend fun addProductToFavorite(
        @Query("productId") productId: String
    ): Response<AddFavoriteResponse>


    @PATCH("/scooby/api/cart/addproduct")
    suspend fun addProductToCart(
        @Query("productId") productId: String
    ): Response<ProductPatch>

    @GET("/scooby/api/cart/getcart")
    suspend fun getCartUser(
    ): Response<CartProductResponse>

    @GET("/scooby/api/cart/getcart")
    suspend fun getCartPayment(
    ): Response<CartProductResponse>

    @DELETE("/scooby/api/cart/removeproduct")
    suspend fun deleteProductFromCart(
        @Query("itemId") productId: String
    ): Response<ProductPatch>

    @Multipart
    @POST("/scooby/api/ocr/product")
    suspend fun sendImageToOCR(
        @Part image: MultipartBody.Part?
    ): Response<ProductResponse>

    @PATCH("/scooby/api/cart/plusquantity")
    suspend fun increaseProductCount(
        @Query("productId") productId: String
    ) : Response<PatchIncreaseProduct>

    @PATCH("/scooby/api/cart/minusquantity")
    suspend fun decreaseProductCount(
        @Query("productId") productId: String
    ) : Response<PatchIncreaseProduct>

    @PATCH("/scooby/api/cart/applycoupon")
    suspend fun applyCoupon(@Body coupon : PatchCoupon ) : Response<PatchCouponRes>


    companion object {
        fun getApi(): ProductApi? {
            return ApiClient.client?.create(ProductApi::class.java)
        }
    }
}