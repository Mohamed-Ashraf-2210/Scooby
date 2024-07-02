package com.example.scooby.scooby.product.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ProductRepo
import com.example.data.utils.Constant
import com.example.domain.CartProductResponse
import com.example.domain.ProductPatch
import com.example.domain.product.ProductResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProductViewModel() : ViewModel() {
    private val productRepo = ProductRepo()

    private val _productResult: MutableLiveData<BaseResponse<ProductResponse>> = MutableLiveData()
    val productResult: LiveData<BaseResponse<ProductResponse>>
        get() = _productResult

    private val _favoriteProductResult: MutableLiveData<ProductResponse> = MutableLiveData()
    val favoriteProductResult: LiveData<ProductResponse>
        get() = _favoriteProductResult


    private val _userCartResult: MutableLiveData<CartProductResponse> = MutableLiveData()
    val userCartResult: MutableLiveData<CartProductResponse>
        get() = _userCartResult

    private val _deleteProductCartResult: MutableLiveData<BaseResponse<ProductPatch>> =
        MutableLiveData()
    val deleteProductCartResult: LiveData<BaseResponse<ProductPatch>>
        get() = _deleteProductCartResult


    fun getProduct() {
        viewModelScope.launch {
            _productResult.value = BaseResponse.Loading()
            try {
                val response = productRepo.getAllProduct()
                if (response != null && response.isSuccessful) {
                    _productResult.value = BaseResponse.Success(response.body())
                } else {
                    _productResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _productResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getFavoriteProduct() {
        viewModelScope.launch {
            try {
                val response = productRepo.getFavoriteProduct()
                response?.body().let {
                    _favoriteProductResult.value = response?.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS favoriteProduct $e")
            }
        }
    }

    fun addProductToFavorite(productId: String) {
        viewModelScope.launch {
            try {
                productRepo.addProductToFavorites(productId)
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS addProductToFavorite $e")
            }
        }
    }

    fun addProductToCart(productId: String) {
        viewModelScope.launch {
            try {
                productRepo.addProductToCart( productId)
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS addProductToCart $e")
            }
        }
    }

    fun getCartUser() {
        viewModelScope.launch {
            try {
                val response = productRepo.getCartUser()
                response?.body().let {
                    _userCartResult.value = response?.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Get User Product $e")
            }
        }
    }

    fun deleteProductFromCart( productId: String) {
        _deleteProductCartResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = productRepo.deleteProductFromCart(productId)
                if (response?.code() == 200) {
                    _deleteProductCartResult.value = BaseResponse.Success(response.body())
                } else {
                    _deleteProductCartResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Delete Product From Cart $e")
            }

        }
    }


    private val _ocrResult: MutableLiveData<BaseResponse<ProductResponse>> =
        MutableLiveData()
    val ocrResult: LiveData<BaseResponse<ProductResponse>>
        get() = _ocrResult

    fun sendImageToOCR(imagePath: String?) {
        _ocrResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val profileImage: MultipartBody.Part? = if (imagePath != null) {
                    val file = File(imagePath)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                } else {
                    null
                }
                val response = productRepo.sendImageToOCR(profileImage)
                if (response != null && response.isSuccessful) {
                    _ocrResult.value = BaseResponse.Success(response.body())
                } else {
                    _ocrResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _ocrResult.value = BaseResponse.Error(e.message)
            }
        }
    }
}
