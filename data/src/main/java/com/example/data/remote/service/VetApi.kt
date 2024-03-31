package com.example.data.remote.service

import com.example.domain.vet.VetResponse
import com.example.domain.doctors.DoctorsResponse
import com.example.data.remote.apis.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface VetApi {

    @GET("/scooby/api/vet/getallvet")
    suspend fun getAllVet(): Response<VetResponse>

    @GET("/sooby/api/doctors/getdoctors")
    suspend fun getDoctors(): Response<DoctorsResponse>

    companion object{
        fun getApi(): VetApi? {
            return ApiClient.client?.create(VetApi::class.java)
        }
    }
}