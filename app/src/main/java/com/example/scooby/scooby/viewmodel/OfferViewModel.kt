package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.OfferRepo
import com.example.domain.offer.OfferResponse
import kotlinx.coroutines.launch

class OfferViewModel : ViewModel() {
    private val offerRepo = OfferRepo()
    private val _offerResult: MutableLiveData<OfferResponse?> = MutableLiveData()
    val offerResult: LiveData<OfferResponse?>
        get() = _offerResult

    fun getOffer() {
        viewModelScope.launch {
            try {
                val response = offerRepo.getOffer()
                if (response != null) {
                    _offerResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}