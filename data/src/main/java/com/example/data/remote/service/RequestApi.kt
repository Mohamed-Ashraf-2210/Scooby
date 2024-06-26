package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.request.AddRequestResponse
import com.example.domain.request.AddResuestRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestApi {

    @POST("/scooby/api/request/addRequest")
    suspend fun addRequest(@Body addResuestRequest: AddResuestRequest): Response<AddRequestResponse>

    companion object {
        fun getApi(): RequestApi? {
            return ApiClient.client?.create(RequestApi::class.java)
        }
    }
}