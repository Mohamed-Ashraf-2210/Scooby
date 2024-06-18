package com.example.data.repository

import com.example.data.remote.service.UserApi
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.SignUpRequest
import com.example.domain.profile.UpdateUseResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
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

    suspend fun getUser() = UserApi.getApi()?.getUser()

    suspend fun updateUser(
        image: File,
        name: String,
        email: String
    ) : Response<UpdateUseResponse>? {
        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val emailRequestBody =
            RequestBody.create("text/plain".toMediaTypeOrNull(), email)
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), image)
        val imagePart = MultipartBody.Part.createFormData("image", image.name, requestFile)

        return UserApi.getApi()?.updateUser(imagePart, nameRequestBody, emailRequestBody)
    }
}