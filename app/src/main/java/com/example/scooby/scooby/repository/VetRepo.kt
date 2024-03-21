package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.VetResponse
import com.example.scooby.scooby.data.model.DoctorsResponse
import com.example.scooby.scooby.network.VetApi
import retrofit2.Response

class VetRepo {
    suspend fun getAllVet(): Response<VetResponse>? {
        return VetApi.getApi()?.getAllVet()
    }

    suspend fun getDoctors(): Response<DoctorsResponse>? {
        return VetApi.getApi()?.getDoctors()
    }
}