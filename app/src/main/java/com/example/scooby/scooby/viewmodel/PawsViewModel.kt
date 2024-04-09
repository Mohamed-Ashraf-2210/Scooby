package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.PawsRepo
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import kotlinx.coroutines.launch

class PawsViewModel() : ViewModel() {
    private val pawsRepo = PawsRepo()
    private val _topCollectionRes : MutableLiveData<AdaptionResponse> = MutableLiveData()
     val topCollectionRes :LiveData<AdaptionResponse>
        get() = _topCollectionRes


    private val _catsResult : MutableLiveData<AdaptionCatsResponse> = MutableLiveData()
    val catsResult :LiveData<AdaptionCatsResponse>
        get() = _catsResult


    private val _dogResult : MutableLiveData<AdaptionDogsResponse> = MutableLiveData()
    val dogResult :LiveData<AdaptionDogsResponse>
        get() = _dogResult


    private val _adoptMeResult: MutableLiveData<AdaptionAdoptMeResponse> = MutableLiveData()
    val adoptMeResult :LiveData<AdaptionAdoptMeResponse>
        get() = _adoptMeResult

    fun getTopCollection(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getTopCollection()
                response?.body().let {
                    _topCollectionRes.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Top Collection Adaption $e")
            }
        }
    }


    fun getCats(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getCats()
                response?.body().let {
                    _catsResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Cats Adaption $e")
            }
        }
    }


    fun getDogs(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getDogs()
                response?.body().let {
                    _dogResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Dogs Adaption $e")
            }
        }
    }


    fun getAdoptMe(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getAdoptMe()
                response?.body().let {
                    _adoptMeResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Top Collection Adaption $e")
            }
        }
    }
}