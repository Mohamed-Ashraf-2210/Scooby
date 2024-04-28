package com.example.data.repository

import com.example.data.remote.service.CommunityApi

class CommunityRepo {
    suspend fun getPosts() = CommunityApi.getApi()?.getPosts()

    suspend fun getMyMoments(userId: String) = CommunityApi.getApi()?.getMyMoments(userId)
}