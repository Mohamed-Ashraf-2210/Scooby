package com.example.scooby.scooby.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.CommunityRepo
import com.example.domain.UserMomentResponse
import com.example.domain.UserReviewResponse
import com.example.domain.booking.BookingResponse
import com.example.domain.community.LikePostResponse
import com.example.domain.community.MyMomentsPosts
import com.example.domain.community.PublicPosts
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val communityRepo = CommunityRepo()

    private val _publicPostsResult: MutableLiveData<BaseResponse<PublicPosts>> = MutableLiveData()
    val publicPostsResult: LiveData<BaseResponse<PublicPosts>>
        get() = _publicPostsResult

    private val _myMomentPostsResult: MutableLiveData<BaseResponse<MyMomentsPosts>> =
        MutableLiveData()
    val myMomentPostsResult: LiveData<BaseResponse<MyMomentsPosts>>
        get() = _myMomentPostsResult

    private val _likePostsResult: MutableLiveData<BaseResponse<LikePostResponse>> =
        MutableLiveData()
    val likePostsResult: LiveData<BaseResponse<LikePostResponse>>
        get() = _likePostsResult

    private val _bookingPastResult: MutableLiveData<BaseResponse<BookingResponse>> =
        MutableLiveData()
    val bookingPastResult: LiveData<BaseResponse<BookingResponse>>
        get() = _bookingPastResult

    private val _bookingUpcomingResult: MutableLiveData<BaseResponse<BookingResponse>> =
        MutableLiveData()
    val bookingUpcomingResult: LiveData<BaseResponse<BookingResponse>>
        get() = _bookingUpcomingResult

    private val _userMomentResult: MutableLiveData<BaseResponse<UserMomentResponse>> =
        MutableLiveData()
    val userMomentResult: LiveData<BaseResponse<UserMomentResponse>>
        get() = _userMomentResult


    private val _userReviewResult: MutableLiveData<BaseResponse<UserReviewResponse>> =
        MutableLiveData()
    val userReviewResult: LiveData<BaseResponse<UserReviewResponse>>
        get() = _userReviewResult

    fun getPublicPosts() {
        viewModelScope.launch {
            _publicPostsResult.value = BaseResponse.Loading()
            try {
                val response = communityRepo.getPosts()
                if (response != null && response.isSuccessful) {
                    _publicPostsResult.value = BaseResponse.Success(response.body())
                } else {
                    _publicPostsResult.value = BaseResponse.Error(response?.message())
                }

            } catch (e: Exception) {
                _publicPostsResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getMyMomentPosts() {
        viewModelScope.launch {
            _myMomentPostsResult.value = BaseResponse.Loading()
            try {
                val response = communityRepo.getMyMoments()
                if (response != null && response.isSuccessful) {
                    _myMomentPostsResult.value = BaseResponse.Success(response.body())
                } else {
                    _myMomentPostsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _myMomentPostsResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun likePost(postId: String) {
        viewModelScope.launch {
            _likePostsResult.value = BaseResponse.Loading()
            try {
                val response = communityRepo.likePost(postId)
                if (response != null && response.isSuccessful) {
                    _likePostsResult.value = BaseResponse.Success(response.body())
                } else {
                    _likePostsResult.value = BaseResponse.Error(response?.message())
                }
            } catch (e: Exception) {
                _likePostsResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getPastBooking(){
        _bookingPastResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = communityRepo.getPastBooking()
                if (response != null && response.isSuccessful)
                    _bookingPastResult.value = BaseResponse.Success(response.body())
                else
                    _bookingPastResult.value = BaseResponse.Error(response?.message())
            }catch (e: Exception){
                _bookingPastResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getUpcomingBooking(){
        _bookingUpcomingResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = communityRepo.getUpcomingBooking()
                if (response != null && response.isSuccessful)
                    _bookingUpcomingResult.value = BaseResponse.Success(response.body())
                else
                    _bookingUpcomingResult.value = BaseResponse.Error(response?.message())
            }catch (e: Exception){
                _bookingUpcomingResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getUserMoment(userId : String){
        _userMomentResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = communityRepo.getUserMoment(userId)
                if (response != null && response.isSuccessful){
                    _userMomentResult.value = BaseResponse.Success(response.body())
                } else{
                    _userMomentResult.value = BaseResponse.Error(response?.message())
                    Log.e("ChK_Rev_suc", "ERROR FETCHING URLS found MomentUser ${response?.errorBody()}")
                    Log.e("ChK_Rev_suc",response?.message().toString())
                }
            }catch (e: Exception){
                _userMomentResult.value = BaseResponse.Error(e.message)
                Log.e("ChK_Rev_suc", "ERROR FETCHING URLS found MomentUser $e")
            }
        }
    }

    fun getUserReview(userId : String){
        _userReviewResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = communityRepo.getUserReview(userId)
                if (response != null && response.isSuccessful){
                    _userReviewResult.value = BaseResponse.Success(response.body())
                } else{
                    _userReviewResult.value = BaseResponse.Error(response?.message())
                    Log.e("ChK_Rev_suc", "ERROR FETCHING URLS found ReviewUser ${response?.errorBody()}")
                    Log.e("ChK_Rev_suc",response?.message().toString())
                }
            }catch (e: Exception){
                _userReviewResult.value = BaseResponse.Error(e.message)
                Log.e("ChK_Rev_suc", "ERROR FETCHING URLS found ReviewUser $e")
            }
        }
    }
}