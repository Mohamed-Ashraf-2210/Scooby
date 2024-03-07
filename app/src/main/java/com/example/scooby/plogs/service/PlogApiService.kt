package com.example.scooby.plogs.service

import com.example.scooby.plogs.model.Plogs
import retrofit2.Response
import retrofit2.http.GET

interface plogApiService {
    @GET("/scooby/api/Plogs/getallplogs")
    suspend fun getAllPlogs(): Response<List<Plogs>>
}