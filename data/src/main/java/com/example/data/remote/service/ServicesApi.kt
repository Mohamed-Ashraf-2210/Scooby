package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.services.ServicesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicesApi {
    @GET("/scooby/api/services/getAllServices")
    suspend fun getServices(): Response<ServicesResponse>
    //Get all services

    @GET("/scooby/api/services/getService?{filter}")
    suspend fun getServicesByFilter(
        @Path("filter") filter:String
    ): Response<ServicesResponse>
    //getServicesByFilter


    companion object {
        fun getApi(): ServicesApi? {
            return ApiClient.client?.create(ServicesApi::class.java)
        }
    }
}