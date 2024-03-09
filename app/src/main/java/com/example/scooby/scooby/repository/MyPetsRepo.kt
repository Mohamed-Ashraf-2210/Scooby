package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.MyPetsResponse
import com.example.scooby.scooby.network.MyPetsApi
import retrofit2.Response

class MyPetsRepo {
    suspend fun getMyPets(): Response<MyPetsResponse>? {
        return MyPetsApi.getApi()?.getMyPets()
    }
}