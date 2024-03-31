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