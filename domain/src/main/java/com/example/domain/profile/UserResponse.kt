package com.example.domain.profile

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String,
    @SerializedName("token")
    val token: String
) {
    data class Data(
        @SerializedName("result")
        val result: Result
    ) {
        data class Result(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("_id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("updatedAt")
            val updatedAt: String,
            @SerializedName("__v")
            val v: Int
        )
    }
}