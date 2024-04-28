package com.example.domain


import com.google.gson.annotations.SerializedName

data class ServicesProfileResponse(
    @SerializedName("updatedDoc")
    val updatedDoc:Data ?
) {
    data class Data(
        @SerializedName("about")
        val about: String?,
        @SerializedName("accepted_pet_sizes")
        val acceptedPetSizes: List<String?>?,
        @SerializedName("accepted_pet_types")
        val acceptedPetTypes: List<String?>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("from")
        val from: Int?,
        @SerializedName("icon")
        val icon: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("imagesProfile")
        val imagesProfile: List<String?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("numberOfRate")
        val numberOfRate: Int?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("pricePer")
        val pricePer: String?,
        @SerializedName("question1")
        val question1: List<String?>?,
        @SerializedName("question2")
        val question2: List<String?>?,
        @SerializedName("question3")
        val question3: List<String?>?,
        @SerializedName("rate")
        val rate: Double?,
        @SerializedName("reviewsOfService")
        val reviewsOfService: List<ReviewsOfService?>?,
        @SerializedName("to")
        val to: Int?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class ReviewsOfService(
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
            @SerializedName("service")
            val service: String?,
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
}