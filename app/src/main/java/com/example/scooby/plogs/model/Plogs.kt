package com.example.scooby.plogs.model


import com.google.gson.annotations.SerializedName

data class Plogs(
    @SerializedName("discription")
    val discription: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("plogImage")
    val plogImage: String?
)