package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
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
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
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

    @GET("/scooby/api/fav/getfavpet/{userId}")
    suspend fun getFavoritePet(
        @Path("userId") id: String
    ): Response<AdaptionAdoptMeResponse>
    @PATCH("/scooby/api/fav/addfav/{userId}")
    suspend fun addPetToFavorite(
        @Path("userId") id: String,
        @Query("petId") petId: String
    ): Response<AddFavoriteResponse>

    @GET("/scooby/api/shelters/getShelter/{shelterId}")
    suspend fun getShelterProfile(
        @Path("shelterId") shelterId: String
    ): Response<ShelterProfileResponse>

    @GET("/scooby/api/shelters/petsInShelter/{shelterId}")
    suspend fun getPetShelterProfile(
        @Path("shelterId") shelterId: String
    ): Response<List<PetShelterProfileResponse.PetShelterProfileResponseItem>>

    @GET("/scooby/api/founded/getCats")
    suspend fun getCatsMissing() : Response<CatsResponse>
    @GET("/scooby/api/founded/getDogs")
    suspend fun getDogsMissing() : Response<DogsResponse>



    companion object{
        fun getApi(): PawsApi? {
            return ApiClient.client?.create(PawsApi::class.java)
        }
    }

}