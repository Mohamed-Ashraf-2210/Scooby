package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.data.model.UpdateUseResponse
import com.example.scooby.utils.ApiClient
import okhttp3.MultipartBody
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