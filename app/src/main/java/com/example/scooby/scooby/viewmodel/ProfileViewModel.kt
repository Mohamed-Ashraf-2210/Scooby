package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.UserRepository
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch
import java.io.File

class ProfileViewModel : ViewModel() {
    private val profileRepo = UserRepository()

    // region Get user
    private val _profileResult: MutableLiveData<BaseResponse<UserProfileResponse>> =
        MutableLiveData()
    val profileResult: LiveData<BaseResponse<UserProfileResponse>>
        get() = _profileResult

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
    private val _updateUserResult: MutableLiveData<UpdateUseResponse?> = MutableLiveData()
    val updateUserResult: LiveData<UpdateUseResponse?>
        get() = _updateUserResult

    fun updateUser(userId: String, image: File) {
        viewModelScope.launch {
            try {
                val response = profileRepo.updateUser(userId, image)
                if (response != null && response.isSuccessful) {
                    _updateUserResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS (updateUser View Model) ->>> $e")
            }
        }
    }
    // endregion

}