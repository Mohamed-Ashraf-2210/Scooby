package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("results")
    val results: Int,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("category")
        val category: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("_id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("priceAfterDiscount")
        val priceAfterDiscount: Int,
        @SerializedName("productImage")
        val productImage: String,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("user")
        val user: String,
        @SerializedName("__v")
        val v: Int
    )
}