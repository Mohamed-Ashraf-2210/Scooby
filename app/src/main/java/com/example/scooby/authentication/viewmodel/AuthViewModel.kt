package com.example.scooby.authentication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.repository.UserRepository
import com.example.domain.authentication.CheckCodeRequest
import com.example.domain.authentication.CheckCodeResponse
import com.example.domain.authentication.ForgotPasswordRequest
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.domain.authentication.LoginRequest
import com.example.domain.authentication.ResetPasswordRequest
import com.example.domain.authentication.ResetPasswordResponse
import com.example.domain.authentication.SignUpRequest
import com.example.domain.profile.UserResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository()


    // region Login
    private val _loginResult: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()
    val loginResult: LiveData<BaseResponse<UserResponse>>
        get() = _loginResult

    fun loginUser(email: String, password: String) {
        _loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password)
                val response = userRepository.loginUser(loginRequest)
                if (response?.code() == 201) {
                    _loginResult.value = BaseResponse.Success(response.body())
                } else {
                    _loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _loginResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion


    // region Sign Up
    private val _signUpResult: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()
    val signUpResult: LiveData<BaseResponse<UserResponse>>
        get() = _signUpResult

    fun signUpUser(email: String, name: String, password: String) {
        _signUpResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val signUpRequest = SignUpRequest(email, name, password)
                val response = userRepository.signUpUser(signUpRequest)
                if (response?.code() == 200) {
                    _signUpResult.value = BaseResponse.Success(response.body())
                } else {
                    _signUpResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _signUpResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Forgot Password
    private val _forgotPasswordResult: MutableLiveData<BaseResponse<ForgotPasswordResponse>> =
        MutableLiveData()
    val forgotPasswordResult: LiveData<BaseResponse<ForgotPasswordResponse>>
        get() = _forgotPasswordResult

    fun forgotPassword(email: String) {
        _forgotPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val forgotPasswordRequest = ForgotPasswordRequest(email)
                val response = userRepository.forgotPassword(forgotPasswordRequest)
                if (response?.code() == 200) {
                    _forgotPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    _forgotPasswordResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _forgotPasswordResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Check Code
    private val _checkCodeResult: MutableLiveData<BaseResponse<CheckCodeResponse>> = MutableLiveData()
    val checkCodeResult: LiveData<BaseResponse<CheckCodeResponse>>
        get() = _checkCodeResult
    fun checkCode(code: String) {
        _checkCodeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val checkCodeRequest = CheckCodeRequest(code)
                val response = userRepository.checkCode(checkCodeRequest)
                if (response?.code() == 200) {
                    _checkCodeResult.value = BaseResponse.Success(response.body())
                } else {
                    _checkCodeResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _checkCodeResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion

    // region Check Code
    private val _resetPasswordResult: MutableLiveData<BaseResponse<ResetPasswordResponse>> =
        MutableLiveData()
    val resetPasswordResult: LiveData<BaseResponse<ResetPasswordResponse>>
        get() = _resetPasswordResult
    fun resetPassword(userId: String, password: String, confirmPassword: String) {
        _resetPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val resetPasswordRequest = ResetPasswordRequest(password, confirmPassword)
                val response = userRepository.resetPassword(userId, resetPasswordRequest)
                if (response?.code() == 200) {
                    _resetPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    _resetPasswordResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _resetPasswordResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    // endregion
}