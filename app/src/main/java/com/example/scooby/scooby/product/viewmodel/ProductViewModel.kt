package com.example.scooby.scooby.product.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.utils.Constant
import com.example.data.repository.ProductRepo
import com.example.domain.CartProductResponse
import com.example.domain.ProductPatch
import com.example.domain.product.ProductResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepo = ProductRepo()

    private val _productResult : MutableLiveData<ProductResponse?> = MutableLiveData()
    val productResult: LiveData<ProductResponse?>
        get() = _productResult

    private val _favoriteProductResult: MutableLiveData<ProductResponse> = MutableLiveData()
    val favoriteProductResult: LiveData<ProductResponse>
        get() = _favoriteProductResult


    private val _userCartResult: MutableLiveData<CartProductResponse> = MutableLiveData()
    val userCartResult: MutableLiveData<CartProductResponse>
        get() = _userCartResult

    private val _deleteProductCartResult: MutableLiveData<BaseResponse<ProductPatch>> = MutableLiveData()
    val deleteProductCartResult: LiveData<BaseResponse<ProductPatch>>
        get() = _deleteProductCartResult




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

    fun getFavoriteProduct(userId: String) {
        viewModelScope.launch {
            try {
                val response = productRepo.getFavoriteProduct(userId)
                response?.body().let {
                    _favoriteProductResult.value = response?.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS favoriteProduct $e")
            }
        }
    }

    fun addProductToFavorite(userId: String, productId: String) {
        viewModelScope.launch {
            try {
                productRepo.addProductToFavorites(userId, productId)
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS addProductToFavorite $e")
            }
        }
    }

    fun addProductToCart(userId: String, productId: String) {
        viewModelScope.launch {
            try {
                productRepo.addProductToCart(userId, productId)
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS addProductToCart $e")
            }
        }
    }

    fun getCartUser(userId: String){
        viewModelScope.launch {
            try {
                val response = productRepo.getCartUser(userId)
                response?.body().let {
                    _userCartResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Get User Product $e")
            }
        }
    }

    fun deleteProductFromCart(userId: String, productId: String){
        _deleteProductCartResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = productRepo.deleteProductFromCart(userId,productId)
                if (response?.code() == 200) {
                    _deleteProductCartResult.value = BaseResponse.Success(response.body())
                } else {
                    _deleteProductCartResult.value = BaseResponse.Error(response?.message())
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Delete Product From Cart $e")
            }

        }
    }




}
