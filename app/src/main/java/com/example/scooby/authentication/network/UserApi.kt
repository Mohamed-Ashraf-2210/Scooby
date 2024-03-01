package com.example.scooby.authentication.network

import com.example.scooby.authentication.data.model.CheckCodeRequest
import com.example.scooby.authentication.data.model.CheckCodeResponse
import com.example.scooby.authentication.data.model.ForgotPasswordRequest
import com.example.scooby.authentication.data.model.ForgotPasswordResponse
import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.ResetPasswordRequest
import com.example.scooby.authentication.data.model.ResetPasswordResponse
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.data.model.SignUpRequest
import com.example.scooby.utils.Constant
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