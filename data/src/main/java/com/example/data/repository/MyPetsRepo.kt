package com.example.data.repository

import com.example.domain.pet.MyPetsResponse
import com.example.data.remote.service.MyPetsApi
import retrofit2.Response

class MyPetsRepo {
    suspend fun getMyPets(): Response<MyPetsResponse>? {
        return MyPetsApi.getApi()?.getMyPets()
    }
}