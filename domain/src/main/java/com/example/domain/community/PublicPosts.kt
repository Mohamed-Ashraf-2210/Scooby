package com.example.domain.community


import com.google.gson.annotations.SerializedName

data class PublicPosts(
    @SerializedName("processedPosts")
    val processedPosts: List<ProcessedPost>
) {
    data class ProcessedPost(
        @SerializedName("liked")
        val liked: Boolean,
        @SerializedName("post")
        val post: Post
    ) {
        data class Post(
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("_id")
            val id: String,
            @SerializedName("likes_Id")
            val likesId: List<String>,
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
        )
    }
}