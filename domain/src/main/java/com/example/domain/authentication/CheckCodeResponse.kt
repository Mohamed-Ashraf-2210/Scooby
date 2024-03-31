package com.example.domain.authentication


import com.google.gson.annotations.SerializedName

data class CheckCodeResponse(
    @SerializedName("userId")
    val userId: String
)