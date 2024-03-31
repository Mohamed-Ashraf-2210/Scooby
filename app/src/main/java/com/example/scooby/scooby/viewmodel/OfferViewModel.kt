package com.example.scooby.scooby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.offer.OfferResponse
import com.example.data.repository.OfferRepo
import com.example.data.Constant
import kotlinx.coroutines.launch

class OfferViewModel (application: Application) : AndroidViewModel(application) {
    private val offerRepo = OfferRepo()
    private val _offerResult: MutableLiveData<OfferResponse?> = MutableLiveData()
    val offerResult: LiveData<OfferResponse?>
        get() =_offerResult
    fun getOffer(){
        viewModelScope.launch {
                try {
                    val response = offerRepo.getOffer()
                    if (response != null) {
                        _offerResult.value =response.body()
                    }
                } catch (e: Exception) {
                    Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
                }
        }
    }
}