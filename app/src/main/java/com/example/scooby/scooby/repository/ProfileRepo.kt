package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.data.model.UpdateUseResponse
import com.example.scooby.scooby.network.ProfileApi
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class ProfileRepo {
    suspend fun getUser(userId: String): Response<ProfileDetailsResponse>? {
        return ProfileApi.getApi()?.getUser(userId)
    }

    suspend fun uploadImageProfile(userId: String, profileImage: File): Response<UpdateUseResponse>? {
        return  ProfileApi.getApi()?.uploadImageProfile(userId,profileImage)
    }

}