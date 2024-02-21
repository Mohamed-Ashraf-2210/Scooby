package com.example.scooby.authentication.data.model


import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("confirmPassword")
    val confirmPassword: String,
    @SerializedName("password")
    val password: String
)