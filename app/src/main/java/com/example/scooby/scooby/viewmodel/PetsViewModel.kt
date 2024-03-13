package com.example.scooby.scooby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.scooby.repository.AllPetsRepo
import com.example.scooby.utils.Constant
import kotlinx.coroutines.launch

class PetsViewModel (application: Application) : AndroidViewModel(application) {
    private val allPetsRepo = AllPetsRepo()
    private val _petsResult: MutableLiveData<AllPetsResponse?> = MutableLiveData()
    val petsResult: LiveData<AllPetsResponse?>
        get() =_petsResult
    fun getPets() {
        viewModelScope.launch {
            try {
                val response = allPetsRepo.getAllPets()
                if (response != null) {
                    _petsResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}