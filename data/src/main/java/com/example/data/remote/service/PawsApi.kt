package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.AddFavoriteResponse
import com.example.domain.MissingPetResponse
import com.example.domain.PetShelterProfileResponse
import com.example.domain.ShelterProfileResponse
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.domain.paws.adaption.AdaptionCatsResponse
import com.example.domain.paws.adaption.AdaptionDogsResponse
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.domain.paws.missing.CatsResponse
import com.example.domain.paws.missing.DogsResponse
import com.example.domain.paws.missing.FoundPetPost
import com.example.domain.paws.missing.GetRecentlyResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
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

    @GET("/scooby/api/fav/getfavpet")
    suspend fun getFavoritePet(): Response<AdaptionAdoptMeResponse>
    @PATCH("/scooby/api/fav/addfav")
    suspend fun addPetToFavorite(
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

    @GET("/scooby/api/founded/getRecentlyAdded")
    suspend fun getRecentlyAdded() : Response<GetRecentlyResponse>

    @GET("/scooby/api/founded/getCats")
    suspend fun getCatsMissing() : Response<CatsResponse>
    @GET("/scooby/api/founded/getDogs")
    suspend fun getDogsMissing() : Response<DogsResponse>

    @Multipart
    @POST("/scooby/api/founded/IfoundPet")
    suspend fun iFoundPet(
        @Part petImage : MultipartBody.Part?,
        @Part("description") description : RequestBody,
        @Part("phoneNumber") phoneNumber : RequestBody,
        @Part("locations") locations : RequestBody

    ): Response<FoundPetPost>

    @Multipart
    @POST("/scooby/api/AI/missing")
    suspend fun getMissingPet(
        @Part petImage: MultipartBody.Part?
    ) : Response<MissingPetResponse>

    companion object{
        fun getApi(): PawsApi? {
            return ApiClient.client?.create(PawsApi::class.java)
        }
    }

}