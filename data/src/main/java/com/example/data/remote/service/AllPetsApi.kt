package com.example.data.remote.service

import com.example.domain.pet.PetsResponse
import com.example.data.remote.apis.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface AllPetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<PetsResponse>

    companion object{
        fun getApi(): AllPetsApi? {
            return ApiClient.client?.create(AllPetsApi::class.java)
        }
    }
}