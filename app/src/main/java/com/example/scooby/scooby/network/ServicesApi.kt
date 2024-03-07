package com.example.scooby.scooby.network

import com.example.scooby.utils.ApiClient
import com.example.scooby.scooby.data.model.ServicesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServicesApi {
    @GET("/scooby/api/services/getAllServices")
    suspend fun getServices(): Response<ServicesResponse>

    companion object {
        fun getApi(): ServicesApi? {
            return ApiClient.client?.create(ServicesApi::class.java)
        }
    }
}