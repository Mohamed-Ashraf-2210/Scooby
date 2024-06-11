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
import com.example.domain.pet.MyPetsResponse
import com.example.domain.pet.PetsResponse
import com.example.scooby.utils.BaseResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PetsViewModel : ViewModel() {
    private val petsRepo = PetsRepo()

    private val _myPetsResult: MutableLiveData<BaseResponse<MyPetsResponse>> = MutableLiveData()
    val myPetsResult: LiveData<BaseResponse<MyPetsResponse>>
        get() = _myPetsResult

    private val _petsResult: MutableLiveData<BaseResponse<PetsResponse>> = MutableLiveData()
    val petsResult: LiveData<BaseResponse<PetsResponse>>
        get() = _petsResult

    private val _addPetsResult: MutableLiveData<AddPetResponse?> = MutableLiveData()
    val addPetsResult: LiveData<AddPetResponse?>
        get() = _addPetsResult

    fun getMyPets() {
        viewModelScope.launch {
            _myPetsResult.value = BaseResponse.Loading()
            try {
                val response = petsRepo.getMyPets()
                if (response != null && response.isSuccessful) {
                    _myPetsResult.value = BaseResponse.Success(response.body())
                } else {
                    _myPetsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _myPetsResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getPets() {
        _petsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = petsRepo.getAllPets()
                if (response != null && response.isSuccessful) {
                    _petsResult.value = BaseResponse.Success(response.body())
                } else {
                    _petsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _petsResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun addPet(userId: String, file: File, petData: AddPetData) {
        viewModelScope.launch {
            try {
                val fileReqBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                val part = MultipartBody.Part.createFormData("file", file.name, fileReqBody)

                val response = petsRepo.addPet(userId, part, petDataToRequestBody(petData))
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