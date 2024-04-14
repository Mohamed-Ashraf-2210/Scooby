package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.PetsRepo
import com.example.domain.pet.MyPetsResponse
import kotlinx.coroutines.launch

class MyPetsViewModel : ViewModel() {
    private val petsRepo = PetsRepo()

    private val _myPetsResult: MutableLiveData<MyPetsResponse?> = MutableLiveData()
    val myPetsResult: LiveData<MyPetsResponse?>
        get() = _myPetsResult

    fun getMyPets(userId: String) {
        viewModelScope.launch {
            try {
                val response = petsRepo.getMyPets(userId)
                if (response != null) {
                    if (response.body() != null && response.isSuccessful) {
                        _myPetsResult.value = response.body()
                    } else {
                        Log.e(Constant.MY_TAG, "Network error: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}