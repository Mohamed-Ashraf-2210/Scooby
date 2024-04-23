package com.example.data.repository

import com.example.data.remote.service.PawsApi
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import retrofit2.Response


class PawsRepo {
    suspend fun getTopCollection(): Response<AdaptionResponse>? {
        return PawsApi.getApi()?.getTopCollection()
    }
    suspend fun getCats(): Response<AdaptionCatsResponse>? {
        return PawsApi.getApi()?.getCats()
    }
    suspend fun getDogs(): Response<AdaptionDogsResponse>? {
        return PawsApi.getApi()?.getDogs()
    }
    suspend fun getAdoptMe(): Response<AdaptionAdoptMeResponse>? {
        return PawsApi.getApi()?.getAdoptMe()
    }

    suspend fun getAllShelter(): Response<ShelterResponse>? {
        return PawsApi.getApi()?.getAllShelter()
    }

    suspend fun getAllPetsShelter(): Response<PetsInShelterResponse>? {
        return PawsApi.getApi()?.getAllPetsShelter()
    }


}