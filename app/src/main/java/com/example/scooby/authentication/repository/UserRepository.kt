package com.example.scooby.authentication.repository

import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.SignUpRequest
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.network.UserApi
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<UserResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest)
    }

    suspend fun signUpUser(signUpRequest: SignUpRequest): Response<UserResponse>? {
        return  UserApi.getApi()?.signUpUser(signUpRequest)
    }
}