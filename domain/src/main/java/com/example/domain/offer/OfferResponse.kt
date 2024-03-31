package com.example.domain.offer


import com.google.gson.annotations.SerializedName

data class OfferResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("results")
    val results: Int
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("expireDate")
        val expireDate: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("offerImage")
        val offerImage: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("__v")
        val v: Int
    )
}