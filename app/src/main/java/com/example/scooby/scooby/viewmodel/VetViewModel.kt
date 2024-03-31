package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.doctors.DoctorsResponse
import com.example.domain.vet.VetResponse
import com.example.data.repository.VetRepo
import com.example.data.Constant
import kotlinx.coroutines.launch

class VetViewModel : ViewModel()  {
    private val vetRepo = VetRepo()

    private val _vetResult : MutableLiveData<VetResponse?> = MutableLiveData()
    val vetResult: LiveData<VetResponse?>
        get() =_vetResult

    fun getVet() {
        viewModelScope.launch {
            try {
                val response = vetRepo.getAllVet()
                if (response != null) {
                    _vetResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }


    private val _doctorResult : MutableLiveData<DoctorsResponse?> = MutableLiveData()
    val doctorResult: LiveData<DoctorsResponse?>
        get() =_doctorResult

    fun getDoctors() {
        viewModelScope.launch {
            try {
                val response = vetRepo.getDoctors()
                if (response != null) {
                    _doctorResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}