package com.example.scooby.authentication.data.model


import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("message")
    val message: String
)