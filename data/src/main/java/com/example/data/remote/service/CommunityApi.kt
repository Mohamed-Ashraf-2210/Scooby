package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.community.MyMomentsPosts
import com.example.domain.community.PublicPosts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommunityApi {
    @GET("/scooby/api/community/getAllPosts")
    suspend fun getPosts(): Response<PublicPosts>

    @GET("/scooby/api/community/myMoments/{userId}")
    suspend fun getMyMoments(@Path("userId") userId: String): Response<MyMomentsPosts>

    companion object {
        fun getApi(): CommunityApi? {
            return ApiClient.client?.create(CommunityApi::class.java)
        }
    }
}