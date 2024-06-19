package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.pet.AddPetResponse
import com.example.domain.pet.MyPetsResponse
import com.example.domain.pet.PetsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<PetsResponse>

    @GET("/scooby/api/Pets/getmypets")
    suspend fun getMyPets(): Response<MyPetsResponse>

    @Multipart
    @POST("/scooby/api/Pets/addpet")
    suspend fun addPet(
        @Part("name") name: RequestBody,
        @Part("type") type: RequestBody,
        @Part("birthday") birthday: RequestBody,
        @Part("breed") breed: RequestBody,
        @Part("adoptionDay") adoptionDay: RequestBody,
        @Part("size") size: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("profileBio") profileBio: RequestBody,
        @Part profileImage: MultipartBody.Part?
    ): Response<AddPetResponse>


    companion object {
        fun getApi(): PetsApi? {
            return ApiClient.client?.create(PetsApi::class.java)
        }
    }
}