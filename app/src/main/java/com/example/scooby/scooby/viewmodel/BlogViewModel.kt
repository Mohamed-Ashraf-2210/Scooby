package com.example.scooby.scooby.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.repository.BlogRepo
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class BlogViewModel (application: Application) : AndroidViewModel(application) {
    private val blogRepo = BlogRepo()
    val blogResult : MutableLiveData<BaseResponse<BlogResponse>> = MutableLiveData()

    fun getBlogs(){
        blogResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = blogRepo.getBlogs()
                if (response?.code() == 200) {
                    blogResult.value = BaseResponse.Success(response.body())
                } else {
                    blogResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                blogResult.value = BaseResponse.Error(e.message)
            }
        }
    }

}