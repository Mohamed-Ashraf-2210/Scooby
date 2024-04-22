package com.example.scooby.scooby.product.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.ProductRepo
import com.example.domain.blog.BlogResponse
import com.example.domain.product.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepo = ProductRepo()

    private val _productResult : MutableLiveData<ProductResponse?> = MutableLiveData()
    val productResult: LiveData<ProductResponse?>
        get() = _productResult

    fun getProduct(){
        viewModelScope.launch {
            try {
                val response = productRepo.getAllProduct()
                if (response != null){
                    _productResult.value = response.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }


}