package com.example.data.repository

import com.example.data.remote.service.ServicesApi
import com.example.domain.ServicesProfileResponse
import com.example.domain.ServicesResponse
import retrofit2.Response

class ServicesRepo {
    suspend fun getServices(): Response<ServicesResponse>? {
        return ServicesApi.getApi()?.getServices()
    }

    suspend fun getServicesByFilter(type:String) :Response<ServicesResponse>?{
        return ServicesApi.getApi()?.getServicesByFilter(type)
    }
    suspend fun getServicesProfileData(servicesId:String) :Response<ServicesProfileResponse>?{
        return ServicesApi.getApi()?.getServicesProfileData(servicesId)
    }
}

