package com.example.data.repository

import com.example.domain.vet.VetResponse
import com.example.data.remote.service.VetApi
import com.example.domain.DoctorProfileResponse
import com.example.domain.doctors.DoctorsResponse
import retrofit2.Response

class VetRepo {
    suspend fun getAllVet(): Response<VetResponse>? {
        return VetApi.getApi()?.getAllVet()
    }

    suspend fun getDoctors(): Response<DoctorsResponse>? {
        return VetApi.getApi()?.getDoctors()
    }

    suspend fun getDoctorProfile(doctorId:String):Response<DoctorProfileResponse>?{
        return VetApi.getApi()?.getDoctorProfile(doctorId)
    }
}