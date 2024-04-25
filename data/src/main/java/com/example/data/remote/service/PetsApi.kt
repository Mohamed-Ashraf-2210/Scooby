package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.AddFavoriteResponse
import com.example.domain.pet.AddPetResponse
import com.example.domain.pet.MyPetsResponse
import com.example.domain.pet.PetsResponse
import com.example.domain.product.ProductResponse
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

interface PetsApi {
    @GET("/scooby/api/Pets/getallpets")
    suspend fun getAllPets(): Response<PetsResponse>

    @GET("/scooby/api/Pets/getmypets/{userId}")
    suspend fun getMyPets(@Path("userId") userId: String): Response<MyPetsResponse>

    @Multipart
    @POST("/scooby/api/Pets/addpet/{userId}")
    suspend fun addPet(
        @Path("userId") userId: String,
        @Part image: MultipartBody.Part,
        @Part("petData") petData: RequestBody
    ): Response<AddPetResponse>




    companion object {
        fun getApi(): PetsApi? {
            return ApiClient.client?.create(PetsApi::class.java)
        }
    }
}