package com.example.data.repository

import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.CheckCodeResponse
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.ResetPasswordResponse
import com.example.domain.authentication.SignUpRequest
import com.example.domain.profile.UserResponse
import com.example.data.remote.service.UserApi
import com.example.domain.profile.UserProfileResponse
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UpdateUserData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import java.io.File

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<UserResponse>? {
        return UserApi.getApi()?.loginUser(loginRequest)
    }

    suspend fun signUpUser(signUpRequest: SignUpRequest): Response<UserResponse>? {
        return UserApi.getApi()?.signUpUser(signUpRequest)
    }

    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>? {
        return UserApi.getApi()?.forgotPassword(forgotPasswordRequest)
    }

    suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Response<CheckCodeResponse>? {
        return UserApi.getApi()?.checkCode(checkCodeRequest)
    }

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<ResetPasswordResponse>? {
        return UserApi.getApi()?.resetPassword(resetPasswordRequest)
    }

    suspend fun getUser(userId: String): Response<UserProfileResponse>? {
        return UserApi.getApi()?.getUser(userId)
    }

    suspend fun updateUser(userId: String, image: MultipartBody.Part, name: String, email: String) =
        UserApi.getApi()?.updateUser(userId, image, name, email)
}