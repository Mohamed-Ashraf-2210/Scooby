package com.example.data.repository

import com.example.domain.blog.BlogResponse
import com.example.data.remote.service.BlogApi
import retrofit2.Response

class BlogRepo {
    suspend fun getBlogs(): Response<BlogResponse>? {
        return BlogApi.getApi()?.getBlog()
    }
}