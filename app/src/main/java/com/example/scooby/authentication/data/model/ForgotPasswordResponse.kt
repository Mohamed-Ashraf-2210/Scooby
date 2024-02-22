package com.example.scooby.authentication.data.model


import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("token")
    val token: String
)