package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.scooby.network.BlogApi
import retrofit2.Response

class BlogRepo {
    suspend fun getBlogs(): Response<BlogResponse>? {
        return BlogApi.getApi()?.getBlog()
    }
}