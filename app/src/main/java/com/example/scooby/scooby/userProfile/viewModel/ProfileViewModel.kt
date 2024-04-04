package com.example.scooby.scooby.userProfile.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.UserRepository
import com.example.domain.profile.UserProfileResponse
import com.example.domain.profile.UpdateUseResponse
import com.example.domain.profile.UpdateUserData
import kotlinx.coroutines.launch
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


    private val _uploadImageResult: MutableLiveData<UpdateUseResponse?> = MutableLiveData()
    val uploadImageResult: LiveData<UpdateUseResponse?>
        get() = _uploadImageResult

    fun uploadImage(userId: String, profileImagePart: File) {
        viewModelScope.launch {
            try {
                val response = profileRepo.uploadImageProfile(userId, profileImagePart)
                if (response != null && response.isSuccessful) {
                    _uploadImageResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }



    private val _updateUserResult: MutableLiveData<UpdateUseResponse?> = MutableLiveData()
    val updateUserResult: LiveData<UpdateUseResponse?>
        get() = _updateUserResult
    fun updateUser(userId: String, userData: UpdateUserData) {
        viewModelScope.launch {
            try {
                val response = profileRepo.updateUser(userId, userData)
                if (response != null && response.isSuccessful) {
                    _updateUserResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}