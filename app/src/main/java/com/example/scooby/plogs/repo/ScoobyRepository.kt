package com.example.scooby.plogs.repo

import com.example.scooby.plogs.model.Plogs
import com.example.scooby.plogs.service.plogApiService
import retrofit2.Response

class ScoobyRepository {
    lateinit var plogApiService: plogApiService
    suspend fun getAllPlogs(): Response<List<Plogs>>{
        return plogApiService.getAllPlogs()
    }
}