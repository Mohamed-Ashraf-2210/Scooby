package com.example.data.repository

import com.example.data.remote.service.CommunityApi
import com.example.domain.community.MyMomentsPosts
import com.example.domain.community.PublicPosts
import retrofit2.Response

class CommunityRepo {
    suspend fun getPosts(): Response<PublicPosts>? {
        return CommunityApi.getApi()?.getPosts()
    }

    suspend fun getMyMoments(userId: String): Response<MyMomentsPosts>? {
        return CommunityApi.getApi()?.getMyMoments(userId)
    }
}