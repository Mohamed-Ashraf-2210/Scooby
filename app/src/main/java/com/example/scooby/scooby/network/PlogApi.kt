package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.BlogResponse
import retrofit2.Response
import retrofit2.http.GET


interface BlogApi {
    @GET("/scooby/api/Plogs/getallplogs")
    suspend fun getBlog() : Response<List<BlogResponse>>

    companion object{

    }
}