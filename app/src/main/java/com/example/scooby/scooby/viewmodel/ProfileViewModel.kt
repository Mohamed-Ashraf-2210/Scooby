package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.profile.ProfileDetailsResponse
import com.example.domain.profile.UpdateUseResponse
import com.example.data.repository.ProfileRepo
import com.example.data.Constant
import kotlinx.coroutines.launch
import java.io.File

class ProfileViewModel: ViewModel() {
    private val profileRepo = ProfileRepo()

    // region Get user
    private val _profileResult: MutableLiveData<ProfileDetailsResponse?> = MutableLiveData()
    val profileResult: LiveData<ProfileDetailsResponse?>
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
                val response = profileRepo.uploadImageProfile(userId,profileImagePart)
                if (response != null && response.isSuccessful) {
                    _uploadImageResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}