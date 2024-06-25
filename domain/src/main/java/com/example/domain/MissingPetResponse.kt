package com.example.domain


import com.google.gson.annotations.SerializedName

data class MissingPetResponse(
    @SerializedName("uploadedImage")
    val uploadedImage: String?,
    @SerializedName("similarityArray")
    val similarityArray: List<SimilarityArray?>?
) {
    data class SimilarityArray(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("location")
        val location: String?,
        @SerializedName("phoneNumber")
        val phoneNumber: String?,
        @SerializedName("similarity")
        val similarity: Double?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("userId")
        val userId: UserId?
    ) {
        data class UserId(
            @SerializedName("cards")
            val cards: List<Card?>?,
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("email")
            val email: String?,
            @SerializedName("favPet")
            val favPet: List<String?>?,
            @SerializedName("favProduct")
            val favProduct: List<String?>?,
            @SerializedName("followers")
            val followers: List<Any?>?,
            @SerializedName("following")
            val following: List<Any?>?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("pets")
            val pets: List<String?>?,
            @SerializedName("profileImage")
            val profileImage: String?,
            @SerializedName("role")
            val role: String?,
            @SerializedName("services_id")
            val servicesId: List<String?>?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("__v")
            val v: Int?
        ) {
            data class Card(
                @SerializedName("cardExpireDate")
                val cardExpireDate: String?,
                @SerializedName("cardNumber")
                val cardNumber: String?,
                @SerializedName("hashedCardSecurityCode")
                val hashedCardSecurityCode: String?
            )
        }
    }
}