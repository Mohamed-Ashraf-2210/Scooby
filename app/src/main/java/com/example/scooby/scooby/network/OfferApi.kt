package com.example.scooby.scooby.network

import com.example.scooby.scooby.data.model.OfferResponse
import com.example.scooby.utils.ApiClient
import retrofit2.Response
import retrofit2.http.GET

interface OfferApi {
    @GET("/scooby/api/offer/getalloffer")
    suspend fun getOffer(): Response<OfferResponse>

    companion object{
        fun getApi(): OfferApi? {
            return ApiClient.client?.create(OfferApi::class.java)
        }
    }
}