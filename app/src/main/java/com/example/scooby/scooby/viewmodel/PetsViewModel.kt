package com.example.scooby.scooby.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PetsRepo
import com.example.domain.pet.AddPetData
import com.example.domain.pet.AddPetResponse
import com.example.domain.pet.MyPetsResponse
import com.example.domain.pet.PetsResponse
import com.example.scooby.utils.BaseResponse
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

    private val _addPetsResult: MutableLiveData<BaseResponse<AddPetResponse>> = MutableLiveData()
    val addPetsResult: LiveData<BaseResponse<AddPetResponse>>
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

    fun addPet(petData: AddPetData, imagePath: String?) {
        _addPetsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val name = RequestBody.create("text/plain".toMediaTypeOrNull(), petData.name)
                val type = RequestBody.create("text/plain".toMediaTypeOrNull(), petData.type)
                val birthday =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), petData.birthday)
                val breed = RequestBody.create("text/plain".toMediaTypeOrNull(), petData.breed)
                val adoptionDay =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), petData.adoptionDay)
                val size = RequestBody.create("text/plain".toMediaTypeOrNull(), petData.size)
                val gender = RequestBody.create("text/plain".toMediaTypeOrNull(), petData.gender)
                val profileBio =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), petData.profileBio)
                val petImage: MultipartBody.Part? = if (imagePath != null) {
                    val file = File(imagePath)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                } else {
                    null
                }

                val response = petsRepo.addPet(
                    name,
                    type,
                    birthday,
                    breed,
                    adoptionDay,
                    size,
                    gender,
                    profileBio,
                    petImage
                )
                if (response != null && response.isSuccessful) {
                    _addPetsResult.value = BaseResponse.Success(response.body())
                } else {
                    _addPetsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _addPetsResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}