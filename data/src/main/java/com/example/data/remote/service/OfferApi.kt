package com.example.data.remote.service

import com.example.domain.offer.OfferResponse
import com.example.data.remote.apis.ApiClient
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