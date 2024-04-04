package com.example.data.repository

import com.example.domain.pet.PetsResponse
import com.example.data.remote.service.AllPetsApi
import retrofit2.Response

class AllPetsRepo {
    suspend fun getAllPets(): Response<PetsResponse>? {
        return AllPetsApi.getApi()?.getAllPets()
    }
}