package com.example.scooby.scooby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.utils.BaseResponse
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.repository.ServicesRepository
import kotlinx.coroutines.launch

class ServicesViewModel (application: Application) : AndroidViewModel(application) {
    private val servicesRepository = ServicesRepository()
    val servicesResult : MutableLiveData<BaseResponse<ServicesResponse>> = MutableLiveData()

    fun getServices() {
        servicesResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = servicesRepository.getServices()
                if (response?.code() == 200) {
                    servicesResult.value = BaseResponse.Success(response.body())
                } else {
                    servicesResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                servicesResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}