package com.example.domain.doctors


import com.google.gson.annotations.SerializedName

data class DoctorsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("description")
        val description: String,
        @SerializedName("doctorImage")
        val doctorImage: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("numberOfRate")
        val numberOfRate: Int,
        @SerializedName("rate")
        val rate: Double,
        @SerializedName("review")
        val review: String
    )
}