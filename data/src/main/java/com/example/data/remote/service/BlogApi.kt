package com.example.data.remote.service

import com.example.domain.blog.BlogResponse
import com.example.data.remote.apis.ApiClient
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