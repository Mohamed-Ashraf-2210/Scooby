package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.CheckCodeResponse
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.ResetPasswordResponse
import com.example.domain.authentication.SignUpRequest
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UserProfileResponse
import com.example.domain.profile.UserResponse
import com.example.domain.profile.UserResponseX
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UserApi {
    @POST("/scooby/api/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<UserResponse>

    @POST("/scooby/api/users/signup")
    suspend fun signUpUser(@Body signUpRequest: SignUpRequest): Response<UserResponse>

    @POST("/scooby/api/users/forgotPassword")
    suspend fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("/scooby/api/users/checkCode")
    suspend fun checkCode(@Body checkCodeRequest: CheckCodeRequest): Response<CheckCodeResponse>

    @POST("/scooby/api/users/reset-password/{userId}")
    suspend fun resetPassword(
        @Path("userId") userId: String,
        @Body resetPasswordRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse>

    @GET("/scooby/api/user/getuser")
    suspend fun getUser(): Response<UserProfileResponse>


    @GET("/scooby/api/user/getOneUser/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: String
        ) : Response<UserResponseX>


    @Multipart
    @PATCH("/scooby/api/user/updateuser")
    suspend fun updateUser(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part profileImage: MultipartBody.Part?
    ): Response<UpdateUseResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}