package com.example.scooby.scooby.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.OfferRepo
import com.example.domain.offer.OfferResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class OfferViewModel : ViewModel() {
    private val offerRepo = OfferRepo()
    private val _offerResult: MutableLiveData<BaseResponse<OfferResponse>> = MutableLiveData()
    val offerResult: LiveData<BaseResponse<OfferResponse>>
        get() = _offerResult

    fun getOffer() {
        _offerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = offerRepo.getOffer()
                if (response != null && response.isSuccessful) {
                    _offerResult.value = BaseResponse.Success(response.body())
                } else {
                    _offerResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _offerResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}