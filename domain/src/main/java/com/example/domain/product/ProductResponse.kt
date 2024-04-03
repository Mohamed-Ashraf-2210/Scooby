package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("results")
    val results: Int?,
    @SerializedName("status")
    val status: String?
)