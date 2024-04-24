package com.example.domain


import com.google.gson.annotations.SerializedName

data class PublicPosts(
    @SerializedName("allPosts")
    val allPosts: List<AllPost>
) {
    data class AllPost(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("likes_Id")
        val likesId: List<LikesId>,
        @SerializedName("likesNumber")
        val likesNumber: Int,
        @SerializedName("onlyMe")
        val onlyMe: Boolean,
        @SerializedName("postImage")
        val postImage: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("userId")
        val userId: String,
        @SerializedName("userImage")
        val userImage: String,
        @SerializedName("userName")
        val userName: String,
        @SerializedName("__v")
        val v: Int
    ) {
        data class LikesId(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("favPet")
            val favPet: List<Any>,
            @SerializedName("favProduct")
            val favProduct: List<Any>,
            @SerializedName("followers")
            val followers: List<Any>,
            @SerializedName("following")
            val following: List<Any>,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("pets")
            val pets: List<Any>,
            @SerializedName("profileImage")
            val profileImage: String,
            @SerializedName("role")
            val role: String,
            @SerializedName("services_id")
            val servicesId: List<Any>,
            @SerializedName("updatedAt")
            val updatedAt: String,
            @SerializedName("__v")
            val v: Int
        )
    }
}