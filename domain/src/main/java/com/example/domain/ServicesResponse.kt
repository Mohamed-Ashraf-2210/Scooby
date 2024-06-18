package com.example.domain


import com.google.gson.annotations.SerializedName

data class ServicesResponse(
    @SerializedName("shuffledServices")
    val shuffledServices: List<ShuffledService>
) {
    data class ShuffledService(
        @SerializedName("city")
        val city: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("pricePer")
        val pricePer: String?,
        @SerializedName("rate")
        val rate: Double?,
        @SerializedName("serviceImage")
        val serviceImage: String?,
        @SerializedName("serviceProfile")
        val serviceProfile: ServiceProfile?,
        @SerializedName("serviceType")
        val serviceType: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class ServiceProfile(
            @SerializedName("about")
            val about: String?,
            @SerializedName("accepted_pet_sizes")
            val acceptedPetSizes: List<String?>?,
            @SerializedName("accepted_pet_types")
            val acceptedPetTypes: List<String?>?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("from")
            val from: Int?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("imagesProfile")
            val imagesProfile: List<String?>?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("numberOfRate")
            val numberOfRate: Int?,
            @SerializedName("price")
            val price: Int?,
            @SerializedName("pricePer")
            val pricePer: String?,
            @SerializedName("question1")
            val question1: List<String?>?,
            @SerializedName("question2")
            val question2: List<String?>?,
            @SerializedName("question3")
            val question3: List<String?>?,
            @SerializedName("rate")
            val rate: Double?,
            @SerializedName("serviceProfile")
            val serviceProfile: String?,
            @SerializedName("to")
            val to: Int?,
            @SerializedName("__v")
            val v: Int?
        )
    }
}