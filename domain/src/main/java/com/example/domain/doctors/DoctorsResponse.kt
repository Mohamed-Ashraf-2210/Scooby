package com.example.domain.doctors


import com.google.gson.annotations.SerializedName

data class DoctorsResponse(
    @SerializedName("doctors")
    val doctors: List<Doctor>
) {
    data class Doctor(
        @SerializedName("about")
        val about: String?,
        @SerializedName("accepted_pet_types")
        val acceptedPetTypes: List<String?>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("doctorImage")
        val doctorImage: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("imagesProfile")
        val imagesProfile: List<String?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("numberOfRate")
        val numberOfRate: Int?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("rate")
        val rate: Double?,
        @SerializedName("review")
        val review: String?,
        @SerializedName("reviewsOfDoctor")
        val reviewsOfDoctor: List<ReviewsOfDoctor?>?,
        @SerializedName("specialized_in")
        val specializedIn: List<String?>?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class ReviewsOfDoctor(
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("doctor")
            val doctor: String?,
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