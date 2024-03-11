package com.example.scooby.scooby.repository

import com.example.scooby.scooby.data.model.OfferResponse
import com.example.scooby.scooby.network.OfferApi
import retrofit2.Response

class OfferRepo {
    suspend fun getOffer() : Response<OfferResponse>? {
        return OfferApi.getApi()?.getOffer()
    }
}