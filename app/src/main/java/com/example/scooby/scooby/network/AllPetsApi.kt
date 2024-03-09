package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.utils.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface AllPetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<AllPetsResponse>

    companion object{
        fun getApi(): AllPetsApi? {
            return ApiClient.client?.create(AllPetsApi::class.java)
        }
    }
}