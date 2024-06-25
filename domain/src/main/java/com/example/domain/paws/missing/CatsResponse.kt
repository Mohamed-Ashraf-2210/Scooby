package com.example.domain.paws.missing


import com.google.gson.annotations.SerializedName

data class CatsResponse(
    @SerializedName("length")
    val length: Int?,
    @SerializedName("shuffledCats")
    val shuffledCats: List<ShuffledCat>
) {
    data class ShuffledCat(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("locations")
        val locations: String?,
        @SerializedName("petImage")
        val petImage: String?,
        @SerializedName("phoneNumber")
        val phoneNumber: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("userId")
        val userId: UserId?,
        @SerializedName("__v")
        val v: Int?
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
            @SerializedName("_id")
            val id: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("passwordResetCode")
            val passwordResetCode: String?,
            @SerializedName("passwordResetExpires")
            val passwordResetExpires: String?,
            @SerializedName("passwordResetVerified")
            val passwordResetVerified: Boolean?,
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