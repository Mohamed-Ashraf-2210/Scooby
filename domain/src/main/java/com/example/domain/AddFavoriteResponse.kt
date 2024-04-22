package com.example.domain


import com.google.gson.annotations.SerializedName

data class AddFavoriteResponse(
    @SerializedName("data")
    val `data`: List<String?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)