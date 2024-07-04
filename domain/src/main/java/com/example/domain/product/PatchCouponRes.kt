package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class PatchCouponRes(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("numOfCartItems")
    val numOfCartItems: Int?,
    @SerializedName("status")
    val status: String?
) {
    data class Data(
        @SerializedName("cartItems")
        val cartItems: List<CartItem?>?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("totalCartPrice")
        val totalCartPrice: Float?,
        @SerializedName("totalPriceAfterDiscount")
        val totalPriceAfterDiscount: Float?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("user")
        val user: String?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class CartItem(
            @SerializedName("_id")
            val id: String?,
            @SerializedName("price")
            val price: Float?,
            @SerializedName("product")
            val product: String?,
            @SerializedName("quantity")
            val quantity: Int?
        )
    }
}