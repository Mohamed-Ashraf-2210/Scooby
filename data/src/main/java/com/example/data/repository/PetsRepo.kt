package com.example.data.repository

import com.example.data.remote.service.PetsApi
import com.example.domain.pet.AddPetData
import com.example.domain.pet.AddPetResponse
import retrofit2.Response

class PetsRepo {
    suspend fun getAllPets() = PetsApi.getApi()?.getAllPets()

    suspend fun getMyPets(userId: String) = PetsApi.getApi()?.getMyPets(userId)

    suspend fun addPet(userId: String, petData: AddPetData): Response<AddPetResponse>? {
        return PetsApi.getApi()?.addPet(
            userId,
            petData.petImage,
            petData.name,
            petData.type,
            petData.birthday,
            petData.breed,
            petData.gender,
            petData.profileBio,
            petData.adoptionDay,
            petData.size
        )
    }
}