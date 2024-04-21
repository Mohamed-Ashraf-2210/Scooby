package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import com.example.domain.product.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PawsApi {
    @GET("/scooby/api/Pets/get-top-collection")
    suspend fun getTopCollection() : Response<AdaptionResponse>

    @GET("/scooby/api/Pets/getcats")
    suspend fun getCats() : Response<AdaptionCatsResponse>

    @GET("/scooby/api/Pets/getdogs")
    suspend fun getDogs() : Response<AdaptionDogsResponse>

    @GET("/scooby/api/Pets/adoptMe")
    suspend fun getAdoptMe() : Response<AdaptionAdoptMeResponse>


    @GET("/scooby/api/shelters/allShelters")
    suspend fun getAllShelter() : Response<ShelterResponse>

    @GET("/scooby/api/shelters/petsInShelters")
    suspend fun getAllPetsShelter() : Response<PetsInShelterResponse>

    @GET("/scooby/api/fav/getfav/{id}")
    suspend fun getFavoritePets(
        @Query("userId") id: String
    ) : Response<AdaptionAdoptMeResponse>

    companion object{
        fun getApi(): PawsApi? {
            return ApiClient.client?.create(PawsApi::class.java)
        }
    }

}