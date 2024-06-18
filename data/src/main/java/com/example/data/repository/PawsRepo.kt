package com.example.data.repository

import com.example.data.remote.service.PawsApi
import com.example.domain.AddFavoriteResponse
import com.example.domain.PetShelterProfileResponse
import com.example.domain.ShelterProfileResponse
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.domain.paws.adaption.AdaptionCatsResponse
import com.example.domain.paws.adaption.AdaptionDogsResponse
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.domain.paws.missing.CatsResponse
import com.example.domain.paws.missing.DogsResponse
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


    suspend fun getFavoritePet(userId:String): Response<AdaptionAdoptMeResponse>? {
        return PawsApi.getApi()?.getFavoritePet(userId)
    }

    suspend fun addPetToFavorite(userId:String, petId:String): Response<AddFavoriteResponse>? {
        return PawsApi.getApi()?.addPetToFavorite(userId, petId)
    }
    suspend fun getShelterProfile(shelterId:String): Response<ShelterProfileResponse>? {
        return PawsApi.getApi()?.getShelterProfile(shelterId)
    }

    suspend fun getPetShelterProfile(shelterId:String): Response<List<PetShelterProfileResponse.PetShelterProfileResponseItem>>? {
        return PawsApi.getApi()?.getPetShelterProfile(shelterId)
    }
    suspend fun getCatsMissing():Response<CatsResponse>? {
        return PawsApi.getApi()?.getCatsMissing()
    }
    suspend fun getDogsMissing():Response<DogsResponse>? {
        return PawsApi.getApi()?.getDogsMissing()
    }







}