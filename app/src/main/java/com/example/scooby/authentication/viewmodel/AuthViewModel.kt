package com.example.scooby.authentication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.utils.BaseResponse
import com.example.scooby.authentication.data.model.CheckCodeRequest
import com.example.scooby.authentication.data.model.CheckCodeResponse
import com.example.scooby.authentication.data.model.ForgotPasswordRequest
import com.example.scooby.authentication.data.model.ForgotPasswordResponse
import com.example.scooby.authentication.data.model.LoginRequest
import com.example.scooby.authentication.data.model.ResetPasswordRequest
import com.example.scooby.authentication.data.model.ResetPasswordResponse
import com.example.scooby.authentication.data.model.SignUpRequest
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel (application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository()


    // region Login
    val loginResult : MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = userRepository.loginUser(loginRequest)
                if (response?.code() == 201) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                loginResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion


    // region Sign Up
    val signUpResult : MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()

    fun signUpUser(email: String, name: String, password: String) {
        signUpResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val signUpRequest = SignUpRequest(email, name, password)
                val response = userRepository.signUpUser(signUpRequest)
                if (response?.code() == 200) {
                    signUpResult.value = BaseResponse.Success(response.body())
                } else {
                    signUpResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                signUpResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Forgot Password
    val forgotPasswordResult : MutableLiveData<BaseResponse<ForgotPasswordResponse>> = MutableLiveData()
    fun forgotPassword(email: String) {
        forgotPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val forgotPasswordRequest = ForgotPasswordRequest(email)
                val response = userRepository.forgotPassword(forgotPasswordRequest)
                if (response?.code() == 200) {
                    forgotPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    forgotPasswordResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                forgotPasswordResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Check Code
    val checkCodeResult : MutableLiveData<BaseResponse<CheckCodeResponse>> = MutableLiveData()
    fun checkCode(code: String) {
        checkCodeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val checkCodeRequest = CheckCodeRequest(code)
                val response = userRepository.checkCode(checkCodeRequest)
                if (response?.code() == 200) {
                    checkCodeResult.value = BaseResponse.Success(response.body())
                } else {
                    checkCodeResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                checkCodeResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Check Code
    val resetPasswordResult : MutableLiveData<BaseResponse<ResetPasswordResponse>> = MutableLiveData()
    fun resetPassword(password: String, confirmPassword: String) {
        resetPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val resetPasswordRequest = ResetPasswordRequest(password, confirmPassword)
                val response = userRepository.resetPassword(resetPasswordRequest)
                if (response?.code() == 200) {
                    resetPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    resetPasswordResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                resetPasswordResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion
}