package com.example.data.repository

import com.example.domain.offer.OfferResponse
import com.example.data.remote.service.OfferApi
import retrofit2.Response

class OfferRepo {
    suspend fun getOffer() : Response<OfferResponse>? {
        return OfferApi.getApi()?.getOffer()
    }
}