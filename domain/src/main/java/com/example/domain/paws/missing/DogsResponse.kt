package com.example.domain.paws.missing


import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("dogs")
    val dogs: List<Dog?>?,
    @SerializedName("length")
    val length: Int?
) {
    data class Dog(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("locations")
        val locations: Locations?,
        @SerializedName("petImage")
        val petImage: String?,
        @SerializedName("phoneNumber")
        val phoneNumber: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("userId")
        val userId: String?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class Locations(
            @SerializedName("coordinates")
            val coordinates: List<Double?>?,
            @SerializedName("type")
            val type: String?
        )
    }
}