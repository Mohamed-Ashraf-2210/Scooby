package com.example.scooby.plogs.model


import com.example.scooby.plogs.model.Plogs
import com.google.gson.annotations.SerializedName

data class PlogsResponse(
    @SerializedName("data")
    val `data`: List<Plogs?>?,
    @SerializedName("status")
    val status: String?
)