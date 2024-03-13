package com.example.scooby.scooby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.MyPetsResponse
import com.example.scooby.scooby.repository.MyPetsRepo
import com.example.scooby.utils.Constant
import kotlinx.coroutines.launch

class MyPetsViewModel (application: Application) : AndroidViewModel(application) {
    private val myPetsRepo = MyPetsRepo()
    private val _myPetsResult: MutableLiveData<MyPetsResponse?> = MutableLiveData()
    val myPetsResult: LiveData<MyPetsResponse?>
        get() =_myPetsResult
    fun getMyPets() {
        viewModelScope.launch {
            try {
                val response = myPetsRepo.getMyPets()
                if (response != null) {
                    _myPetsResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}