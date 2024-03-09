package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.MyPetsResponse
import com.example.scooby.utils.ApiClient
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