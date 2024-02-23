package com.example.scooby.authentication.data.model


import com.google.gson.annotations.SerializedName

data class CheckCodeRequest(
    @SerializedName("code")
    val code: String
)