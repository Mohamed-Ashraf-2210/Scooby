package com.example.domain


import com.google.gson.annotations.SerializedName

data class UserReviewResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("result")
    val result: Int?,
    @SerializedName("status")
    val status: String?
) {
    data class Data(
        @SerializedName("Reviews")
        val reviews: List<Review?>?
    ) {
        data class Review(
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
            @SerializedName("service")
            val service: String?,
            @SerializedName("shelter")
            val shelter: String?,
            @SerializedName("user")
            val user: User?
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