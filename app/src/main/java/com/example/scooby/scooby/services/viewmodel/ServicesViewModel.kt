package com.example.scooby.scooby.services.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ServicesRepo
import com.example.data.Constant
import com.example.domain.ServicesProfileResponse
import com.example.domain.services.ServicesResponse
import kotlinx.coroutines.launch

class ServicesViewModel() : ViewModel() {
    private val servicesRepo = ServicesRepo()
    private val _servicesResult : MutableLiveData<ServicesResponse?> = MutableLiveData()
    val servicesResult: LiveData<ServicesResponse?>
        get() =_servicesResult

    private val _servicesProfileResult : MutableLiveData<ServicesProfileResponse?> = MutableLiveData()
    val servicesProfileResult:LiveData<ServicesProfileResponse?>
        get() = _servicesProfileResult



    fun getServices() {
        viewModelScope.launch {
            try {
                val response = servicesRepo.getServices()
                if (response != null) {
                    _servicesResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }

    fun getServicesProfile(servicesId:String){
        viewModelScope.launch {
            val response = servicesRepo.getServicesProfileData(servicesId)
            try {
                if (response != null && response.isSuccessful){
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