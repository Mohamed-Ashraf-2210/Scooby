package com.example.domain.pet


import com.google.gson.annotations.SerializedName

data class AddPetResponse(
    @SerializedName("data")
    val `data`: Data,
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
        @SerializedName("size")
        val size: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("user")
        val user: String,
        @SerializedName("vaccinations_id")
        val vaccinationsId: List<Any>
    )
}