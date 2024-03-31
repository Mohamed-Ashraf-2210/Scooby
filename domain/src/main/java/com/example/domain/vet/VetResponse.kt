package com.example.domain.vet


import com.google.gson.annotations.SerializedName

data class VetResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("results")
    val results: Int
) {
    data class Data(
        @SerializedName("bio")
        val bio: String,
        @SerializedName("callNumber")
        val callNumber: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("locations")
        val locations: Locations,
        @SerializedName("numberOfRate")
        val numberOfRate: String,
        @SerializedName("rate")
        val rate: Double,
        @SerializedName("review")
        val review: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("__v")
        val v: Int,
        @SerializedName("vetImage")
        val vetImage: String,
        @SerializedName("vetName")
        val vetName: String
    ) {
        data class Locations(
            @SerializedName("address")
            val address: String,
            @SerializedName("coordinates")
            val coordinates: List<Double>,
            @SerializedName("type")
            val type: String
        )
    }
}