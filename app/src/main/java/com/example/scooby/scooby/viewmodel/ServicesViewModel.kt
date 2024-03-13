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

class ServicesViewModel() : ViewModel() {
    private val servicesRepo = ServicesRepo()
    private val _servicesResult : MutableLiveData<ServicesResponse?> = MutableLiveData()
    val servicesResult: LiveData<ServicesResponse?>
        get() =_servicesResult

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
}