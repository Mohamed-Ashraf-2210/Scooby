package com.example.scooby.scooby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.blog.BlogResponse
import com.example.data.repository.BlogRepo
import com.example.data.Constant
import kotlinx.coroutines.launch

class BlogViewModel (application: Application) : AndroidViewModel(application) {
     private val blogRepo = BlogRepo()
     private val _blogResult : MutableLiveData<BlogResponse?> = MutableLiveData()
    val blogResult:LiveData<BlogResponse?>
        get() = _blogResult

    fun getBlogs(){
        viewModelScope.launch {
            try {
                val response = blogRepo.getBlogs()
                if (response != null) {
                    _blogResult.value =response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }

}