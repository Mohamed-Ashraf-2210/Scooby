package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.network.ProfileApi
import retrofit2.Response

class ProfileRepo {
    suspend fun getUser(userId: String): Response<ProfileDetailsResponse>? {
        return ProfileApi.getApi()?.getUser(userId)
    }

}