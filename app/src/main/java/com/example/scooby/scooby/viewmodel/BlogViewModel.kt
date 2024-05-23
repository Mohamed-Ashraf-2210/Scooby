package com.example.scooby.scooby.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.BlogRepo
import com.example.domain.blog.BlogResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class BlogViewModel() : ViewModel() {
    private val blogRepo = BlogRepo()
    private val _blogResult: MutableLiveData<BaseResponse<BlogResponse>> = MutableLiveData()
    val blogResult: LiveData<BaseResponse<BlogResponse>>
        get() = _blogResult

    fun getBlogs() {
        _blogResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = blogRepo.getBlogs()
                if (response != null && response.isSuccessful) {
                    _blogResult.value = BaseResponse.Success(response.body())
                } else {
                    _blogResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _blogResult.value = BaseResponse.Error(e.message)
            }
        }
    }

}