package com.example.domain.authentication


import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("confirmPassword")
    val confirmPassword: String,
    @SerializedName("password")
    val password: String
)