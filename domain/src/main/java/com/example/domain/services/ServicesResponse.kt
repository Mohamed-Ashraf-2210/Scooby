package com.example.domain.services


import com.google.gson.annotations.SerializedName

data class ServicesResponse(

    @SerializedName("shuffledServices")
    val shuffledServices: List<ShuffledService>
) {
    data class ShuffledService(
        @SerializedName("city")
        val city: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("pricePer")
        val pricePer: String?,
        @SerializedName("rate")
        val rate: Double?,
        @SerializedName("serviceImage")
        val serviceImage: String?,
        @SerializedName("serviceProfile")
        val serviceProfile: String?,
        @SerializedName("serviceType")
        val serviceType: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("__v")
        val v: Int?
    )
}