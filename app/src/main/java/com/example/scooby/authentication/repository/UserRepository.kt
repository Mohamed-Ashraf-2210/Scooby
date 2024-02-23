package com.example.scooby.authentication.repository

import com.example.scooby.authentication.data.model.CheckCodeRequest
import com.example.scooby.authentication.data.model.CheckCodeResponse
import com.example.scooby.authentication.data.model.ForgotPasswordRequest
import com.example.scooby.authentication.data.model.ForgotPasswordResponse
import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.ResetPasswordRequest
import com.example.scooby.authentication.data.model.ResetPasswordResponse
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

    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>? {
        return  UserApi.getApi()?.forgotPassword(forgotPasswordRequest)
    }

    suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Response<CheckCodeResponse>? {
        return  UserApi.getApi()?.checkCode(checkCodeRequest)
    }

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<ResetPasswordResponse>? {
        return  UserApi.getApi()?.resetPassword(resetPasswordRequest)
    }
}