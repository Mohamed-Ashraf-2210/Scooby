package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.scooby.network.AllPetsApi
import retrofit2.Response

class AllPetsRepo {
    suspend fun getAllPets(): Response<AllPetsResponse>? {
        return AllPetsApi.getApi()?.getAllPets()
    }
}