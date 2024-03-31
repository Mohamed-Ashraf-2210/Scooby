package com.example.data.repository

import com.example.domain.pet.AllPetsResponse
import com.example.data.remote.service.AllPetsApi
import retrofit2.Response

class AllPetsRepo {
    suspend fun getAllPets(): Response<AllPetsResponse>? {
        return AllPetsApi.getApi()?.getAllPets()
    }
}