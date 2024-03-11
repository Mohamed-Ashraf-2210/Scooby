package com.example.scooby.scooby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.OfferResponse
import com.example.scooby.scooby.repository.OfferRepo
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class OfferViewModel (application: Application) : AndroidViewModel(application) {
    private val offerRepo = OfferRepo()
    val offerResult: MutableLiveData<BaseResponse<OfferResponse>> = MutableLiveData()

    fun getOffer(){
        offerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = offerRepo.getOffer()
                if (response?.code() == 200) {
                    offerResult.value = BaseResponse.Success(response.body())
                } else {
                    offerResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                offerResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}