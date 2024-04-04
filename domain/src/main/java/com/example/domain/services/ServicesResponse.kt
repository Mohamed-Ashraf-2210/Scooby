package com.example.domain.services


import com.google.gson.annotations.SerializedName

data class ServicesResponse(
    @SerializedName("allServices")
    val allServices: List<AllService>
) {
    data class AllService(
        @SerializedName("carImage")
        val carImage: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("licenseImage")
        val licenseImage: String,
        @SerializedName("nationalID")
        val nationalID: String,
        @SerializedName("personalID")
        val personalID: String,
        @SerializedName("phone")
        val phone: Int,
        @SerializedName("price")
        val price: Int,
        @SerializedName("pricePer")
        val pricePer: String,
        @SerializedName("rate")
        val rate: Double,
        @SerializedName("serviceImage")
        val serviceImage: String,
        @SerializedName("serviceType")
        val serviceType: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("__v")
        val v: Int,
        @SerializedName("workplace")
        val workplace: String
    )
}