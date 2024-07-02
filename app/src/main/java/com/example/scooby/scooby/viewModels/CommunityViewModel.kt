package com.example.scooby.scooby.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.CommunityRepo
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
}