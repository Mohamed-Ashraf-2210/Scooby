package com.example.domain


import com.google.gson.annotations.SerializedName

data class ShelterProfileResponse(
    @SerializedName("about")
    val about: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("locations")
    val locations: Locations?,
    @SerializedName("numberOfRates")
    val numberOfRates: Int?,
    @SerializedName("pets_Id")
    val petsId: List<String?>?,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("reviewsOfShelter")
    val reviewsOfShelter: List<ReviewsOfShelter?>?,
    @SerializedName("shelterImage")
    val shelterImage: String?,
    @SerializedName("shelterImages")
    val shelterImages: List<String?>?,
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

    data class ReviewsOfShelter(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("rating")
        val rating: Double?,
        @SerializedName("review")
        val review: String?,
        @SerializedName("shelter")
        val shelter: String?,
        @SerializedName("user")
        val user: User?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class User(
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("profileImage")
            val profileImage: String?
        )
    }
}