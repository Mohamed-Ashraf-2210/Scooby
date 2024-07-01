package com.example.data.repository

import com.example.data.remote.service.UserApi
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.SignUpRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest) = UserApi.getApi()?.loginUser(loginRequest)

    suspend fun signUpUser(signUpRequest: SignUpRequest) =
        UserApi.getApi()?.signUpUser(signUpRequest)

    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest) =
        UserApi.getApi()?.forgotPassword(forgotPasswordRequest)

    suspend fun checkCode(checkCodeRequest: CheckCodeRequest) =
        UserApi.getApi()?.checkCode(checkCodeRequest)

    suspend fun resetPassword(userId: String, resetPasswordRequest: ResetPasswordRequest) =
        UserApi.getApi()?.resetPassword(userId, resetPasswordRequest)

    suspend fun getUser() = UserApi.getApi()?.getUser()

    suspend fun getUserById(userId: String) = UserApi.getApi()?.getUserById(userId)

    suspend fun updateUser(
        name: RequestBody,
        email: RequestBody,
        profileImage: MultipartBody.Part?
    ) = UserApi.getApi()?.updateUser(name, email, profileImage)

}