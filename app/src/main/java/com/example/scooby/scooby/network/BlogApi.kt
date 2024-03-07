package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.utils.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface BlogApi {
    @GET("/scooby/api/Plogs/getallplogs")
    suspend fun getBlog(): Response<BlogResponse>

    companion object{
        fun getApi(): BlogApi? {
            return ApiClient.client?.create(BlogApi::class.java)
        }
    }
}