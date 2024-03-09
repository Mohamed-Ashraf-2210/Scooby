package com.example.scooby.scooby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.scooby.data.model.MyPetsResponse
import com.example.scooby.scooby.repository.AllPetsRepo
import com.example.scooby.scooby.repository.MyPetsRepo
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class MyPetsViewModel (application: Application) : AndroidViewModel(application) {
    private val myPetsRepo = MyPetsRepo()
    val myPetsResult: MutableLiveData<BaseResponse<MyPetsResponse>> = MutableLiveData()

    fun getMyPets() {
        myPetsResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = myPetsRepo.getMyPets()
                if (response?.code() == 200) {
                    myPetsResult.value = BaseResponse.Success(response.body())
                } else {
                    myPetsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                myPetsResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}