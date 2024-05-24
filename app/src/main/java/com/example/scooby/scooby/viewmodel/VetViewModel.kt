package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.VetRepo
import com.example.domain.DoctorProfileResponse
import com.example.domain.doctors.DoctorsResponse
import com.example.domain.vet.VetResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class VetViewModel : ViewModel() {
    private val vetRepo = VetRepo()

    private val _vetResult: MutableLiveData<BaseResponse<VetResponse>> = MutableLiveData()
    val vetResult: LiveData<BaseResponse<VetResponse>>
        get() = _vetResult


    private val _doctorProfileResult: MutableLiveData<DoctorProfileResponse?> = MutableLiveData()
    val doctorProfileResult: LiveData<DoctorProfileResponse?>
        get() = _doctorProfileResult


    fun getVet() {
        viewModelScope.launch {
            _vetResult.value = BaseResponse.Loading()
            try {
                val response = vetRepo.getAllVet()
                if (response != null && response.isSuccessful) {
                    _vetResult.value = BaseResponse.Success(response.body())
                } else {
                    _vetResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _vetResult.value = BaseResponse.Error(e.message)
            }
        }
    }


    private val _doctorResult: MutableLiveData<DoctorsResponse?> = MutableLiveData()
    val doctorResult: LiveData<DoctorsResponse?>
        get() = _doctorResult

    fun getDoctors() {
        viewModelScope.launch {
            try {
                val response = vetRepo.getDoctors()
                if (response != null) {
                    _doctorResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Doctors List $e")
            }
        }
    }

    fun getDoctorProfile(doctorId: String) {
        viewModelScope.launch {
            try {
                val response = vetRepo.getDoctorProfile(doctorId)
                response?.body().let {
                    _doctorProfileResult.value = it
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Doctor Profile $e")
            }
        }
    }
}