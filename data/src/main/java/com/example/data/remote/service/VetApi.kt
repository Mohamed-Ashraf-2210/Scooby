package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.DoctorProfileResponse
import com.example.domain.doctors.DoctorsResponse
import com.example.domain.vet.VetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VetApi {

    @GET("/scooby/api/vet/getallvet")
    suspend fun getAllVet(): Response<VetResponse>

    @GET("/scooby/api/doctors/getdoctors")
    suspend fun getDoctors(): Response<DoctorsResponse>

    @GET("/scooby/api/doctors/get-doctor/{doctorId}")
    suspend fun getDoctorProfile(
        @Path("doctorId") doctorId: String
    ): Response<DoctorProfileResponse>

    companion object{
        fun getApi(): VetApi? {
            return ApiClient.client?.create(VetApi::class.java)
        }
    }
}