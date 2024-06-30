package com.example.scooby.scooby.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.service.UserApi
import com.example.data.repository.UserRepository
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UserProfileResponse
import com.example.domain.profile.UserResponseX
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileViewModel : ViewModel() {
    private val profileRepo = UserRepository()
    // region Get user
    private val _profileResult: MutableLiveData<BaseResponse<UserProfileResponse>> =
        MutableLiveData()
    val profileResult: LiveData<BaseResponse<UserProfileResponse>>
        get() = _profileResult

    private val _userDetailsResult: MutableLiveData<BaseResponse<UserResponseX>> =
        MutableLiveData()
    val userDetailsResult: LiveData<BaseResponse<UserResponseX>>
        get() = _userDetailsResult


    fun getUserInfo() {
        _profileResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = profileRepo.getUser()
                if (response != null && response.isSuccessful) {
                    _profileResult.value = BaseResponse.Success(response.body())
                } else {
                    _profileResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _profileResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion


    // region Update user
    private val _updateUserResult: MutableLiveData<BaseResponse<UpdateUseResponse>> =
        MutableLiveData()
    val updateUserResult: LiveData<BaseResponse<UpdateUseResponse>>
        get() = _updateUserResult

    fun updateUser(name: String, email: String, imagePath: String?) {
        _updateUserResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val nameUser = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
                val emailUser = RequestBody.create("text/plain".toMediaTypeOrNull(), email)

                val profileImage: MultipartBody.Part? = if (imagePath != null) {
                    val file = File(imagePath)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                } else {
                    null
                }
                val response = profileRepo.updateUser(nameUser, emailUser, profileImage)
                if (response != null && response.isSuccessful) {
                    _updateUserResult.value = BaseResponse.Success(response.body())
                } else {
                    _updateUserResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _updateUserResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion


    fun getUserById(userId:String){
        _userDetailsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = profileRepo.getUserById(userId)
                if (response != null && response.isSuccessful)
                    _userDetailsResult.value = BaseResponse.Success(response.body())
                    else
                    _userDetailsResult.value = BaseResponse.Error(response?.message())
            }catch (e: Exception){
                _userDetailsResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}