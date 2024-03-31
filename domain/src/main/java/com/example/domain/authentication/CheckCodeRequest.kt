package com.example.domain.authentication


import com.google.gson.annotations.SerializedName

data class CheckCodeRequest(
    @SerializedName("code")
    val code: String
)