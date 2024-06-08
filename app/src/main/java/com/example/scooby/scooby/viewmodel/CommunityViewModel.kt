package com.example.scooby.scooby.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.CommunityRepo
import com.example.domain.community.LikePostResponse
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

    private val _likePostsResult: MutableLiveData<LikePostResponse?> = MutableLiveData()
    val likePostsResult: LiveData<LikePostResponse?>
        get() = _likePostsResult

    fun getPublicPosts() {
        viewModelScope.launch {
            try {
                communityRepo.getPosts()?.apply {
                    if (isSuccessful) {
                        _publicPostsResult.value = body()
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS (communityRepo) $e")
            }
        }
    }

    fun getMyMomentPosts() {
        viewModelScope.launch {
            try {
                communityRepo.getMyMoments()?.apply {
                    if (isSuccessful) {
                        _myMomentPostsResult.value = body()
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }

    fun likePost(postId: String) {
        viewModelScope.launch {
            try {
                communityRepo.likePost(postId)?.apply {
                    if (isSuccessful) {
                        _likePostsResult.value = body()
                    }
                }
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS $e")
            }
        }
    }
}