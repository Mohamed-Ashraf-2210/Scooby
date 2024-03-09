package com.example.scooby.scooby.data.model


import com.google.gson.annotations.SerializedName
// @GET("/scooby/api/users/allUser")
data class AllUserResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("passwordResetCode")
        val passwordResetCode: String,
        @SerializedName("passwordResetExpires")
        val passwordResetExpires: String,
        @SerializedName("passwordResetVerified")
        val passwordResetVerified: Boolean,
        @SerializedName("pets")
        val pets: List<String>,
        @SerializedName("profileImage")
        val profileImage: String,
        @SerializedName("services_id")
        val servicesId: List<String>,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("__v")
        val v: Int
    )
}