package com.example.scooby.authentication.data.model


import com.google.gson.annotations.SerializedName

data class CheckCodeResponse(
    @SerializedName("userId")
    val userId: String
)