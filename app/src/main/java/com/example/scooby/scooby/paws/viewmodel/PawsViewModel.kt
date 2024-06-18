package com.example.scooby.scooby.paws.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.PawsRepo
import com.example.domain.PetShelterProfileResponse
import com.example.domain.ShelterProfileResponse
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.domain.paws.adaption.AdaptionCatsResponse
import com.example.domain.paws.adaption.AdaptionDogsResponse
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import kotlinx.coroutines.launch

class PawsViewModel : ViewModel() {
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


    private val _shelterResult: MutableLiveData<ShelterResponse> = MutableLiveData()
    val shelterResult :LiveData<ShelterResponse>
        get() = _shelterResult


    private val _petsShelterResult: MutableLiveData<PetsInShelterResponse> = MutableLiveData()
    val petsShelterResult :LiveData<PetsInShelterResponse>
        get() = _petsShelterResult


    private val _adoptMeResult: MutableLiveData<AdaptionAdoptMeResponse> = MutableLiveData()
    val adoptMeResult :LiveData<AdaptionAdoptMeResponse>
        get() = _adoptMeResult


    private val _favoritePetResult: MutableLiveData<AdaptionAdoptMeResponse> = MutableLiveData()
    val favoritePetResult: LiveData<AdaptionAdoptMeResponse>
        get() = _favoritePetResult

    private val _shelterProfileResult: MutableLiveData<ShelterProfileResponse> = MutableLiveData()
    val shelterProfileResult: LiveData<ShelterProfileResponse>
        get() = _shelterProfileResult


    private val _petShelterProfileResult: MutableLiveData<List<PetShelterProfileResponse.PetShelterProfileResponseItem>> = MutableLiveData()
    val petShelterProfileResult: LiveData<List<PetShelterProfileResponse.PetShelterProfileResponseItem>>
        get() = _petShelterProfileResult


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

    fun getShelters(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getAllShelter()
                response?.body().let {
                    _shelterResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS All shelter Adaption $e")
            }
        }
    }

    fun getPetsShelters(){
        viewModelScope.launch {
            try {
                val response = pawsRepo.getAllPetsShelter()
                response?.body().let {
                    _petsShelterResult.value = response?.body()
                }
            }catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS petsShelterResult Adaption $e")
            }
        }
    }

    fun getFavoritePet(userId: String) {
        viewModelScope.launch {
            try {
                val response = pawsRepo.getFavoritePet(userId)
                response?.body().let {
                    _favoritePetResult.value = response?.body()
                }

            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS favoritePet $e")
            }
        }
    }

    fun addPetToFavorite(userId: String, petId: String) {
        viewModelScope.launch {
            try {
                pawsRepo.addPetToFavorite(userId, petId)
            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS favoritePet $e")
            }
        }
    }
    fun getShelterProfile(shelterId: String) {
        viewModelScope.launch {
            try {
                val response = pawsRepo.getShelterProfile(shelterId)
                response?.body().let {
                    _shelterProfileResult.value = response?.body()
                }

            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Shelter Profile  $e")
            }
        }
    }
    fun getPetShelterProfile(shelterId: String) {
        viewModelScope.launch {
            try {
                val response = pawsRepo.getPetShelterProfile(shelterId)
                response?.body().let {
                    _petShelterProfileResult.value = response?.body()
                }

            } catch (e: Exception) {
                Log.e(Constant.MY_TAG, "ERROR FETCHING URLS Pet Shelter Profile $e")
            }
        }
    }




}