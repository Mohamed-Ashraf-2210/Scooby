package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.CommunityRepo
import com.example.domain.community.MyMomentsPosts
import com.example.domain.community.PublicPosts
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val communityRepo = CommunityRepo()

    private val _publicPostsResult: MutableLiveData<PublicPosts?> = MutableLiveData()
    val publicPostsResult: LiveData<PublicPosts?>
        get() = _publicPostsResult

    private val _myMomentPostsResult: MutableLiveData<MyMomentsPosts?> = MutableLiveData()
    val myMomentPostsResult: LiveData<MyMomentsPosts?>
        get() = _myMomentPostsResult

    fun getPublicPosts() {
        viewModelScope.launch {
            try {
                val response = communityRepo.getPosts()
                Log.e(Constant.MY_TAG, "RESPONSE: ${response?.code().toString()}")

                if (response != null && response.isSuccessful) {
                    _publicPostsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS (communityRepo) $e")
            }
        }
    }

    fun getMyMomentPosts(userId: String) {
        viewModelScope.launch {
            try {
                val response = communityRepo.getMyMoments(userId)
                if (response != null && response.isSuccessful) {
                    _myMomentPostsResult.value = response.body()
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}