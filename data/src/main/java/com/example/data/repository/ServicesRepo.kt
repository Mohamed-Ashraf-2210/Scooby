package com.example.data.repository

import com.example.domain.services.ServicesResponse
import com.example.data.remote.service.ServicesApi
import retrofit2.Response

class ServicesRepo {
    suspend fun getServices(): Response<ServicesResponse>? {
        return ServicesApi.getApi()?.getServices()
    }

    suspend fun getServicesByFilter(type:String) :Response<ServicesResponse>?{
        return ServicesApi.getApi()?.getServicesByFilter(type)
    }
}

