package com.example.domain.profile


import com.google.gson.annotations.SerializedName

data class ProfileDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("data")
        val `data`: Data
    ) {
        data class Data(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("followers")
            val followers: List<Any>,
            @SerializedName("following")
            val following: List<Any>,
            @SerializedName("_id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pets")
            val pets: List<Pet>,
            @SerializedName("profileImage")
            val profileImage: String,
            @SerializedName("services_id")
            val servicesId: List<ServicesId>,
            @SerializedName("updatedAt")
            val updatedAt: String,
            @SerializedName("__v")
            val v: Int
        ) {
            data class Pet(
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

            data class ServicesId(
                @SerializedName("carImage")
                val carImage: String,
                @SerializedName("city")
                val city: String,
                @SerializedName("country")
                val country: String,
                @SerializedName("createdAt")
                val createdAt: String,
                @SerializedName("gender")
                val gender: String,
                @SerializedName("_id")
                val id: String,
                @SerializedName("licenseImage")
                val licenseImage: String,
                @SerializedName("nationalID")
                val nationalID: String,
                @SerializedName("personalID")
                val personalID: String,
                @SerializedName("phone")
                val phone: Int,
                @SerializedName("price")
                val price: Int,
                @SerializedName("rate")
                val rate: Int,
                @SerializedName("serviceImage")
                val serviceImage: String,
                @SerializedName("serviceType")
                val serviceType: String,
                @SerializedName("updatedAt")
                val updatedAt: String,
                @SerializedName("__v")
                val v: Int,
                @SerializedName("workplace")
                val workplace: String
            )
        }
    }
}