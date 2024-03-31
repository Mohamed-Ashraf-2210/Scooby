package com.example.domain.profile


import com.google.gson.annotations.SerializedName

data class UpdateUseResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("data")
        val `data`: Data
    ) {
        data class Data(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("followers")
            val followers: List<Any>,
            @SerializedName("following")
            val following: List<Any>,
            @SerializedName("_id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pets")
            val pets: List<Any>,
            @SerializedName("profileImage")
            val profileImage: String,
            @SerializedName("services_id")
            val servicesId: List<Any>,
            @SerializedName("updatedAt")
            val updatedAt: String,
            @SerializedName("__v")
            val v: Int
        )
    }
}