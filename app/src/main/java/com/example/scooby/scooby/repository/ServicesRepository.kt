package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.ServicesResponse
import com.example.scooby.scooby.network.ServicesApi
import retrofit2.Response

class ServicesRepository {
    suspend fun getServices(): Response<List<ServicesResponse>>? {
        return ServicesApi.getApi()?.getServices()
    }
}