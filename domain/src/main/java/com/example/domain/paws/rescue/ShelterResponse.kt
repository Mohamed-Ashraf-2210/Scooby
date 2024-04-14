package com.example.domain.paws.rescue


import com.google.gson.annotations.SerializedName

data class ShelterResponse(
    @SerializedName("allShelters")
    val allShelters: List<AllShelter>
) {
    data class AllShelter(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("locations")
        val locations: Locations?,
        @SerializedName("numberOfRates")
        val numberOfRates: Int?,
        @SerializedName("pets_Id")
        val petsId: List<String?>?,
        @SerializedName("rate")
        val rate: Double?,
        @SerializedName("shelterImage")
        val shelterImage: String?,
        @SerializedName("shelterName")
        val shelterName: String?,
        @SerializedName("shelterNumber")
        val shelterNumber: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class Locations(
            @SerializedName("address")
            val address: String?,
            @SerializedName("coordinates")
            val coordinates: List<Double?>?,
            @SerializedName("type")
            val type: String?
        )
    }
}