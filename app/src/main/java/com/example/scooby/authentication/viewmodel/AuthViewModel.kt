package com.example.scooby.authentication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel (application: Application) : AndroidViewModel(application) {

    private val userResponse = UserRepository()
    val loginResult : MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = userResponse.loginUser(loginRequest)
                if (response?.code() == 201) { // 200
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                loginResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}