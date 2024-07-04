package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class PatchIncreaseProduct(
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
        val totalCartPrice: Int?,
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
            val price: Int?,
            @SerializedName("product")
            val product: String?,
            @SerializedName("quantity")
            val quantity: Int?
        )
    }
}