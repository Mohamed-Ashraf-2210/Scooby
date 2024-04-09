package com.example.data.remote.service

import com.example.data.remote.apis.ApiClient
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import retrofit2.Response
import retrofit2.http.GET

interface PawsApi {
    @GET("/scooby/api/Pets/get-top-collection")
    suspend fun getTopCollection() : Response<AdaptionResponse>

    @GET("/scooby/api/Pets/getcats")
    suspend fun getCats() : Response<AdaptionCatsResponse>

    @GET("/scooby/api/Pets/getdogs")
    suspend fun getDogs() : Response<AdaptionDogsResponse>

    @GET("/scooby/api/Pets/adoptMe")
    suspend fun getAdoptMe() : Response<AdaptionAdoptMeResponse>

    companion object{
        fun getApi(): PawsApi? {
            return ApiClient.client?.create(PawsApi::class.java)
        }
    }

}