package com.example.domain.authentication


import com.google.gson.annotations.SerializedName

data class CheckCodeResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("userId")
    val userId: String
)