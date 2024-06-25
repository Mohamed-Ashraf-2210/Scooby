package com.example.data.repository

import com.example.data.remote.service.PetsApi
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PetsRepo {
    suspend fun getAllPets() = PetsApi.getApi()?.getAllPets()

    suspend fun getMyPets() = PetsApi.getApi()?.getMyPets()

    suspend fun addPet(
        name: RequestBody,
        type: RequestBody,
        birthday: RequestBody,
        category: RequestBody,
        adoptionDay: RequestBody,
        size: RequestBody,
        gender: RequestBody,
        profileBio: RequestBody,
        petImage: MultipartBody.Part?
    ) = PetsApi.getApi()
        ?.addPet(name, type, birthday, category, adoptionDay, size, gender, profileBio, petImage)

}