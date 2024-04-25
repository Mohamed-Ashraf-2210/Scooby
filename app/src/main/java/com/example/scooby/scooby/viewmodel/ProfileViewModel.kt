package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.UserRepository
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UpdateUserData
import com.example.domain.profile.UserProfileResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ProfileViewModel : ViewModel() {
    private val profileRepo = UserRepository()

    // region Get user
    private val _profileResult: MutableLiveData<UserProfileResponse?> = MutableLiveData()
    val profileResult: LiveData<UserProfileResponse?>
        get() = _profileResult

    fun getUser(userId: String) {
        viewModelScope.launch {
            try {
                val response = profileRepo.getUser(userId)
                if (response != null && response.isSuccessful) {
                    _profileResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
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