package com.example.scooby.scooby.services.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ServicesRepo
import com.example.domain.ServicesProfileResponse
import com.example.domain.services.ServicesResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class ServicesViewModel() : ViewModel() {
    private val servicesRepo = ServicesRepo()


    private val _servicesResult: MutableLiveData<BaseResponse<ServicesResponse>> = MutableLiveData()
    val servicesResult: LiveData<BaseResponse<ServicesResponse>>
        get() = _servicesResult

    private val _servicesProfileResult: MutableLiveData<ServicesProfileResponse?> =
        MutableLiveData()
    val servicesProfileResult: LiveData<ServicesProfileResponse?>
        get() = _servicesProfileResult


    fun getServices() {
        _servicesResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = servicesRepo.getServices()
                if (response != null && response.isSuccessful) {
                    _servicesResult.value = BaseResponse.Success(response.body())
                } else {
                    _servicesResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _servicesResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getServicesProfile(servicesId: String) {
        viewModelScope.launch {
            val response = servicesRepo.getServicesProfileData(servicesId)
            try {
                if (response != null && response.isSuccessful) {
                    _servicesProfileResult.value = response.body()
                }
                Log.d("TEST_FILTER", "Response code: ${response?.code()}")
                Log.d("TEST_FILTER", "Response body: ${response?.body()}")
            } catch (e: Exception) {
                Log.e("TEST_FILTER", "ERROR FETCHING URLS $e")
            }

        }
    }


}