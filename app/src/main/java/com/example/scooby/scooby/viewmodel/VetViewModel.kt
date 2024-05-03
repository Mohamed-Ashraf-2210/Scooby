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
import kotlinx.coroutines.launch

class VetViewModel : ViewModel() {
    private val vetRepo = VetRepo()

    private val _vetResult: MutableLiveData<VetResponse?> = MutableLiveData()
    val vetResult: LiveData<VetResponse?>
        get() = _vetResult

    private val _error: MutableLiveData<String?> = MutableLiveData()
    val error: LiveData<String?>
        get() = _error

    private val _doctorProfileResult : MutableLiveData<DoctorProfileResponse?> = MutableLiveData()
    val doctorProfileResult : LiveData<DoctorProfileResponse?>
        get() = _doctorProfileResult


    fun getVet() {
        viewModelScope.launch {
            try {
                val response = vetRepo.getAllVet()
                if (response != null) {
                    if (response.isSuccessful && response.body() != null) {
                        _vetResult.value = response.body()
                    } else {
                        _error.value = "Network error: ${response.code()}"
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
                _error.value = "Unexpected error: $e"
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

    fun getDoctorProfile(doctorId : String){
        viewModelScope.launch {
            try {
                val response = vetRepo.getDoctorProfile(doctorId)
                response?.body().let {
                    _doctorProfileResult.value = it
                }
            }catch( e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Doctor Profile $e")
            }
        }
    }
}