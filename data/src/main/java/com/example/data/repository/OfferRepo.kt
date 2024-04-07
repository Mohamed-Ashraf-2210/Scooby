package com.example.data.repository

import com.example.data.remote.service.OfferApi
import com.example.domain.offer.OfferResponse
import retrofit2.Response

class OfferRepo {
    suspend fun getOffer(): Response<OfferResponse>? {
        return OfferApi.getApi()?.getOffer()
    }
}