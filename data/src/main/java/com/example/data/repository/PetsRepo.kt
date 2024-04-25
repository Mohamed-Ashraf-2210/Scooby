package com.example.data.repository

import com.example.data.remote.service.PetsApi
import com.example.domain.pet.AddPetResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class PetsRepo {
    suspend fun getAllPets() = PetsApi.getApi()?.getAllPets()

    suspend fun getMyPets(userId: String) = PetsApi.getApi()?.getMyPets(userId)

    suspend fun addPet(
        userId: String,
        image: MultipartBody.Part,
        requestBody: RequestBody
    ): Response<AddPetResponse>? {
        return PetsApi.getApi()?.addPet(userId, image, requestBody)
    }

}