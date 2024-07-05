package com.example.data.repository

import com.example.data.remote.service.ProductApi
import com.example.domain.AddFavoriteResponse
import com.example.domain.CartProductResponse
import com.example.domain.ProductPatch
import com.example.domain.product.PatchCoupon
import com.example.domain.product.ProductResponse
import okhttp3.MultipartBody
import retrofit2.Response


class ProductRepo {
    suspend fun getAllProduct(): Response<ProductResponse>? {
        return ProductApi.getApi()?.getAllProduct()
    }

    suspend fun getFavoriteProduct(): Response<ProductResponse>? {
        return ProductApi.getApi()?.getFavoriteProduct()
    }

    suspend fun addProductToFavorites(
        productId: String
    ): Response<AddFavoriteResponse>? {
        return ProductApi.getApi()?.addProductToFavorite(productId)
    }

    suspend fun addProductToCart(productId: String): Response<ProductPatch>? {
        return ProductApi.getApi()?.addProductToCart(productId)
    }

    suspend fun getCartUser(): Response<CartProductResponse>? {
        return ProductApi.getApi()?.getCartUser()
    }

    suspend fun getCartPayment(): Response<CartProductResponse>? {
        return ProductApi.getApi()?.getCartPayment()
    }

    suspend fun deleteProductFromCart(productId: String): Response<ProductPatch>? {
        return ProductApi.getApi()?.deleteProductFromCart(productId)
    }

    suspend fun sendImageToOCR(
        image: MultipartBody.Part?
    ) = ProductApi.getApi()?.sendImageToOCR(image)

    suspend fun increaseProductCount(
        productId: String
    ) = ProductApi.getApi()?.increaseProductCount(productId)

    suspend fun decreaseProductCount(
        productId: String
    ) = ProductApi.getApi()?.decreaseProductCount(productId)

    suspend fun applyCoupon(coupon:PatchCoupon) = ProductApi.getApi()?.applyCoupon(coupon)

    suspend fun getCheckoutUrl(cartId:String) = ProductApi.getApi()?.getCheckoutUrl(cartId)
}