package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.utils.ApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {

    @GET("/scooby/api/user/getuser/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<ProfileDetailsResponse>

    companion object {
        fun getApi(): ProfileApi? {
            return ApiClient.client?.create(ProfileApi::class.java)
        }
    }
}