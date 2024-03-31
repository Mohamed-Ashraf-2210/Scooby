package com.example.data.remote.service

import com.example.domain.profile.ProfileDetailsResponse
import com.example.domain.profile.UpdateUseResponse
import com.example.data.remote.apis.ApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

interface ProfileApi {

    @GET("/scooby/api/user/getuser/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<ProfileDetailsResponse>

    @PATCH("/scooby/api/user/updateuser/{userId}")
    suspend fun uploadImageProfile(
        @Path("userId") userId: String,
        @Part profileImage: File,
    ): Response<UpdateUseResponse>


        companion object {
        fun getApi(): ProfileApi? {
            return ApiClient.client?.create(ProfileApi::class.java)
        }
    }
}