package com.example.data.remote.service

import com.example.domain.pet.MyPetsResponse
import com.example.data.remote.apis.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface MyPetsApi {
    @GET("/scooby/api/Pets/getmypets")
    suspend fun getMyPets(): Response<MyPetsResponse>

    companion object{
        fun getApi(): MyPetsApi? {
            return ApiClient.client?.create(MyPetsApi::class.java)
        }
    }
}