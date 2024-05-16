package com.example.data.repository

import com.example.data.remote.service.UserApi
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.SignUpRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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

    suspend fun getUser(userId: String) = UserApi.getApi()?.getUser(userId)

    suspend fun updateUser(
        userId: String,
        image: File
    ) =
        UserApi.getApi()?.updateUser(
            userId,
            image = MultipartBody.Part.createFormData(
                "profileImage",
                image.name,
                image.asRequestBody()
            )
        )
}