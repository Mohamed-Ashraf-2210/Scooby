package com.example.scooby.scooby.data.model


import com.google.gson.annotations.SerializedName

data class BlogResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("description")
        val description: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("blogImage")
        val blogImage: String
    )
}