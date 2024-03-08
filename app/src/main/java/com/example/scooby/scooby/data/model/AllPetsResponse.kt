package com.example.scooby.scooby.data.model


import com.google.gson.annotations.SerializedName

/*
* petimage
* name
* owner
* */
data class AllPetsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("adoptionDay")
        val adoptionDay: String,
        @SerializedName("birthday")
        val birthday: String,
        @SerializedName("category")
        val category: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("owner")
        val owner: String,
        @SerializedName("petimage") // petImage
        val petimage: String,
        @SerializedName("profileBio")
        val profileBio: String,
        @SerializedName("size")
        val size: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("user")
        val user: User,
        @SerializedName("vaccinations_id")
        val vaccinationsId: List<Any>,
        @SerializedName("weigth") // weight
        val weigth: Int
    ) {
        data class User(
            @SerializedName("_id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pets")
            val pets: List<String>,
            @SerializedName("profileImage")
            val profileImage: String,
            @SerializedName("services_id")
            val servicesId: List<String>
        )
    }
}