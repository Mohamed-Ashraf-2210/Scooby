package com.example.data.repository

import com.example.domain.profile.ProfileDetailsResponse
import com.example.domain.profile.UpdateUseResponse
import com.example.data.remote.service.ProfileApi
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