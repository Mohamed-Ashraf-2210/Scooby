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
                petsRepo.getMyPets(userId).apply {
                    if (this != null && this.isSuccessful) {
                        _myPetsResult.value = this.body()
                    } else {
                        Log.e(Constant.MY_TAG, "Network error: (getMyPets) ->>> ${this?.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS (getMyPets) ->>> $e")
            }
        }
    }
}