package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.PetsRepo
import com.example.domain.pet.AddPetData
import com.example.domain.pet.AddPetResponse
import com.example.domain.pet.PetsResponse
import kotlinx.coroutines.launch

class PetsViewModel : ViewModel() {
    private val petsRepo = PetsRepo()
    private val _petsResult: MutableLiveData<PetsResponse?> = MutableLiveData()
    val petsResult: LiveData<PetsResponse?>
        get() = _petsResult

    private val _addPetsResult: MutableLiveData<AddPetResponse?> = MutableLiveData()
    val addPetsResult: LiveData<AddPetResponse?>
        get() = _addPetsResult

    fun getPets() {
        viewModelScope.launch {
            try {
                val response = petsRepo.getAllPets()
                if (response != null) {
                    _petsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }

    fun addPet(userId: String, petData: AddPetData){
        viewModelScope.launch {
            try {
                val response = petsRepo.addPet(userId, petData)
                if (response != null && response.isSuccessful) {
                    _addPetsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}