package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.VetResponse
import com.example.scooby.scooby.data.model.DoctorsResponse
import com.example.scooby.utils.ApiClient
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