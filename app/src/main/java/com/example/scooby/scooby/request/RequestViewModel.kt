package com.example.scooby.scooby.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RequestRepo
import com.example.domain.request.AddRequestResponse
import com.example.domain.request.AddResuestRequest
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class RequestViewModel : ViewModel() {
    private val requestRepo = RequestRepo()

    private val _requestResult: MutableLiveData<BaseResponse<AddRequestResponse>> =
        MutableLiveData()
    val requestResult: LiveData<BaseResponse<AddRequestResponse>>
        get() = _requestResult

    private fun sendDataRequest(addResuestRequest: AddResuestRequest) {
        _requestResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = requestRepo.addRequest(addResuestRequest)
                if (response?.code() == 200) {
                    _requestResult.value = BaseResponse.Success(response.body())
                } else {
                    _requestResult.value = BaseResponse.Error(response?.message())
                }

            } catch (e: Exception) {
                _requestResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}