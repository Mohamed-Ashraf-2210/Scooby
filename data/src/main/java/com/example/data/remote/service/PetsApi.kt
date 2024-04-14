package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.pet.AddPetResponse
import com.example.domain.pet.MyPetsResponse
import com.example.domain.pet.PetsResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<PetsResponse>

    @GET("/scooby/api/Pets/getmypets/{userId}")
    suspend fun getMyPets(@Path("userId") userId: String): Response<MyPetsResponse>

    @Multipart
    @POST("/scooby/api/Pets/addpet/{userId}")
    suspend fun addPet(
        @Path("userId") userId: String,
        @Part petData: AddPetData
    )
    companion object{
        @Part("petImage") petImage: MultipartBody.Part,
        @Part("name") name: String,
        @Part("type") type: String,
        @Part("birthday") birthday: String,
        @Part("breed") breed: String,
        @Part("gender") gender: String,
        @Part("profileBio") profileBio: String,
        @Part("adoptionDay") adoptionDay: String,
        @Part("size") size: String,
    ): Response<AddPetResponse>

    companion object {
        fun getApi(): PetsApi? {
            return ApiClient.client?.create(PetsApi::class.java)
        }
    }
}