package com.example.scooby.authentication.repository

import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.LoginResponse
import com.example.scooby.authentication.network.UserApi
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}