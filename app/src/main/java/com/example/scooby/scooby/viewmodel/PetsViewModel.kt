package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.AllPetsRepo
import com.example.domain.pet.AllPetsResponse
import kotlinx.coroutines.launch

class PetsViewModel : ViewModel() {
    private val allPetsRepo = AllPetsRepo()
    private val _petsResult: MutableLiveData<AllPetsResponse?> = MutableLiveData()
    val petsResult: LiveData<AllPetsResponse?>
        get() = _petsResult

    fun getPets() {
        viewModelScope.launch {
            try {
                val response = allPetsRepo.getAllPets()
                if (response != null) {
                    _petsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}