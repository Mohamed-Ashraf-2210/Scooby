package com.example.scooby.scooby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.scooby.repository.AllPetsRepo
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class PetsViewModel (application: Application) : AndroidViewModel(application) {
    private val allPetsRepo = AllPetsRepo()
    val petsResult: MutableLiveData<BaseResponse<AllPetsResponse>> = MutableLiveData()

    fun getPets() {
        petsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = allPetsRepo.getAllPets()
                if (response?.code() == 200) {
                    petsResult.value = BaseResponse.Success(response.body())
                } else {
                    petsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                petsResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}