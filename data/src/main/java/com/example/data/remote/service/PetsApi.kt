package com.example.data.remote.service

import com.example.domain.pet.PetsResponse
import com.example.data.remote.apis.ApiClient
import com.example.domain.pet.AddPetData
import com.example.domain.profile.UpdateUserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<PetsResponse>

    @Multipart
    @POST("/scooby/api/Pets/addpet/{userId}")
    suspend fun addPet(
        @Path("userId") userId: String,
        @Part petData: AddPetData
    )

    companion object{
        fun getApi(): PetsApi? {
            return ApiClient.client?.create(PetsApi::class.java)
        }
    }
}