package com.example.scooby.scooby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.repository.ProfileRepo
import com.example.scooby.utils.Constant
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val profileRepo = ProfileRepo()
    private val _profileResult: MutableLiveData<ProfileDetailsResponse?> = MutableLiveData()
    val profileResult: LiveData<ProfileDetailsResponse?>
        get() = _profileResult

    fun getUser() {
        viewModelScope.launch {
            try {
                val response = profileRepo.getUser()
                if (response != null) {
                    _profileResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}