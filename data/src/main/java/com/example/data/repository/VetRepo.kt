package com.example.data.repository

import com.example.domain.vet.VetResponse
import com.example.domain.doctors.DoctorsResponse
import com.example.data.remote.service.VetApi
import retrofit2.Response

class VetRepo {
    suspend fun getAllVet(): Response<VetResponse>? {
        return VetApi.getApi()?.getAllVet()
    }

    suspend fun getDoctors(): Response<DoctorsResponse>? {
        return VetApi.getApi()?.getDoctors()
    }
}