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
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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

    fun addPet(userId: String, file: File, petData: AddPetData){
        viewModelScope.launch {
            try {
                val fileReqBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                val part = MultipartBody.Part.createFormData("file", file.name, fileReqBody)

                val response = petsRepo.addPet(userId, part,petDataToRequestBody(petData))
                if (response != null && response.isSuccessful) {
                    _addPetsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }

    private fun petDataToRequestBody(petData: AddPetData): RequestBody {
        val gson = Gson()
        val petDataJson = gson.toJson(petData)
        return RequestBody.create("application/json".toMediaTypeOrNull(), petDataJson)
    }
}