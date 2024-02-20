package com.example.scooby.authentication.network

import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.data.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/scooby/api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<UserResponse>

    @POST("/scooby/api/users/signup")
    suspend fun signUpUser(@Body signUpRequest: SignUpRequest): Response<UserResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}