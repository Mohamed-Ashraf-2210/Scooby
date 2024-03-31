package com.example.domain.authentication


import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("message")
    val message: String
)