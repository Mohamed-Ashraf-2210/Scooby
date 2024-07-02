package com.example.data.repository

import com.example.data.remote.service.CommunityApi

class CommunityRepo {
    suspend fun getPosts() = CommunityApi.getApi()?.getPosts()

    suspend fun getMyMoments() = CommunityApi.getApi()?.getMyMoments()

    suspend fun likePost(postId: String) = CommunityApi.getApi()?.likePost(postId)

    suspend fun getPastBooking() = CommunityApi.getApi()?.getPastBooking()

    suspend fun getUpcomingBooking() = CommunityApi.getApi()?.getUpcomingBooking()

    suspend fun getUserMoment(userId : String) = CommunityApi.getApi()?.getUserMoment(userId)

    suspend fun getUserReview(userId : String) = CommunityApi.getApi()?.getUserReview(userId)
}