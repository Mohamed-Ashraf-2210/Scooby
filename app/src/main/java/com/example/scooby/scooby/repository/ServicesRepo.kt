package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.network.ServicesApi
import retrofit2.Response

class ServicesRepo {
    suspend fun getServices(): Response<ServicesResponse>? {
        return ServicesApi.getApi()?.getServices()
    }

    suspend fun getServicesByFilter(type:String) :Response<ServicesResponse>?{
        return ServicesApi.getApi()?.getServicesByFilter(type)
    }
}

