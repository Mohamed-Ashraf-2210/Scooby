package com.example.domain.pet


import com.google.gson.annotations.SerializedName


data class PetsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("adoptionDay")
        val adoptionDay: String,
        @SerializedName("availableForAdoption")
        val availableForAdoption: Boolean,
        @SerializedName("birthday")
        val birthday: String,
        @SerializedName("category")
        val category: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("owner")
        val owner: String,
        @SerializedName("petImage")
        val petImage: String,
        @SerializedName("profileBio")
        val profileBio: String,
        @SerializedName("shelterInfo")
        val shelterInfo: String,
        @SerializedName("size")
        val size: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("user")
        val user: User,
        @SerializedName("vaccinations_id")
        val vaccinationsId: List<Any>,
        @SerializedName("weight")
        val weight: Int
    ) {
        data class User(
            @SerializedName("followers")
            val followers: List<Any>,
            @SerializedName("following")
            val following: List<Any>,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("passwordResetCode")
            val passwordResetCode: String,
            @SerializedName("passwordResetExpires")
            val passwordResetExpires: String,
            @SerializedName("passwordResetVerified")
            val passwordResetVerified: Boolean,
            @SerializedName("pets")
            val pets: List<String>,
            @SerializedName("profileImage")
            val profileImage: String,
            @SerializedName("role")
            val role: String,
            @SerializedName("services_id")
            val servicesId: List<String>
        )
    }
}