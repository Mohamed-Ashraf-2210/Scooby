package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.CheckCodeResponse
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.ResetPasswordResponse
import com.example.domain.profile.UserResponse
import com.example.domain.authentication.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/scooby/api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<UserResponse>

    @POST("/scooby/api/users/signup")
    suspend fun signUpUser(@Body signUpRequest: SignUpRequest): Response<UserResponse>

    @POST("/scooby/api/users/forgotPassword")
    suspend fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("/scooby/api/users/checkCode")
    suspend fun checkCode(@Body checkCodeRequest: CheckCodeRequest): Response<CheckCodeResponse>

    @POST("/scooby/api/users/reset-password/65db566868eec600486f06a5")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Response<ResetPasswordResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}