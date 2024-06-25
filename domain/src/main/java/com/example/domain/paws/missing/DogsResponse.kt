package com.example.domain.paws.missing


import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("dogs")
    val dogs: List<Dog>,
    @SerializedName("length")
    val length: Int?
) {
    data class Dog(
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
            @SerializedName("bio")
            val bio: String?,
            @SerializedName("cards")
            val cards: List<Any?>?,
            @SerializedName("email")
            val email: String?,
            @SerializedName("favPet")
            val favPet: List<String?>?,
            @SerializedName("favProduct")
            val favProduct: List<String?>?,
            @SerializedName("firstName")
            val firstName: String?,
            @SerializedName("followers")
            val followers: List<Any?>?,
            @SerializedName("following")
            val following: List<Any?>?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("lastName")
            val lastName: String?,
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
            val servicesId: List<Any?>?,
            @SerializedName("__v")
            val v: Int?
        )
    }
}