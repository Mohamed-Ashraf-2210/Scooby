package com.example.data.repository

import com.example.data.remote.service.RequestApi
import com.example.domain.request.AddResuestRequest

class RequestRepo {
    suspend fun addRequest(addResuestRequest: AddResuestRequest) =
        RequestApi.getApi()?.addRequest(addResuestRequest)
}