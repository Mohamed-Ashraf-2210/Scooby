package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.repository.ServicesRepo
import com.example.scooby.utils.Constant
import kotlinx.coroutines.launch
import retrofit2.Response

class ServicesViewModel() : ViewModel() {
    private val servicesRepo = ServicesRepo()
    private val _servicesResult : MutableLiveData<ServicesResponse?> = MutableLiveData()
    val servicesResult: LiveData<ServicesResponse?>
        get() =_servicesResult

    private val _servicesResultByFilter : MutableLiveData<ServicesResponse?> = MutableLiveData()
    val servicesResultByFilter:LiveData<ServicesResponse?>
        get() = _servicesResultByFilter




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

    fun getServicesByFilter(type:String){
        viewModelScope.launch {
            val response = servicesRepo.getServicesByFilter(type)
            try {
                if (response != null && response.isSuccessful){
                    _servicesResultByFilter.value = response.body()
                }
                Log.d("TEST_FILTER", "Response code: ${response?.code()}")
                Log.d("TEST_FILTER", "Response body: ${response?.body()}")
            } catch (e: Exception) {
                Log.e("TEST_FILTER", "ERROR FETCHING URLS $e")
            }


        }
    }


}