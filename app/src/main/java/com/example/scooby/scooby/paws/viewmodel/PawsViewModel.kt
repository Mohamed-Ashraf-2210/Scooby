package com.example.scooby.scooby.paws.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Constant
import com.example.data.repository.PawsRepo
import com.example.domain.MissingPetResponse
import com.example.domain.PetShelterProfileResponse
import com.example.domain.ShelterProfileResponse
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.domain.paws.adaption.AdaptionCatsResponse
import com.example.domain.paws.adaption.AdaptionDogsResponse
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.domain.paws.missing.CatsResponse
import com.example.domain.paws.missing.DogsResponse
import com.example.domain.paws.missing.FoundPetPost
import com.example.domain.paws.missing.GetRecentlyResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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

    // This Belong To Missing Part in PawsFragment
    private val _catsMissingResult : MutableLiveData<BaseResponse<CatsResponse>> = MutableLiveData()
    val catsMissingResult :LiveData<BaseResponse<CatsResponse>>
        get() = _catsMissingResult

    private val _dogsMissingResult : MutableLiveData<BaseResponse<DogsResponse>> = MutableLiveData()
    val dogsMissingResult :LiveData<BaseResponse<DogsResponse>>
        get() = _dogsMissingResult

    private val _recentlyMissingResult : MutableLiveData<BaseResponse<GetRecentlyResponse>> = MutableLiveData()
    val recentlyMissingResult :LiveData<BaseResponse<GetRecentlyResponse>>
        get() = _recentlyMissingResult


    private val _iFoundPetResult : MutableLiveData<BaseResponse<FoundPetPost>> = MutableLiveData()
    val iFoundPetResult : LiveData<BaseResponse<FoundPetPost>>
        get() = _iFoundPetResult


    private val _missingPetResult : MutableLiveData<BaseResponse<MissingPetResponse>> = MutableLiveData()
    val missingPetResult : MutableLiveData<BaseResponse<MissingPetResponse>>
        get() = _missingPetResult

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

    fun getCatsMissing(){
        _catsMissingResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = pawsRepo.getCatsMissing()
                if(response != null && response.isSuccessful){
                    _catsMissingResult.value = BaseResponse.Success(response.body())
                }else{
                    _catsMissingResult.value = BaseResponse.Error(response?.message())
                }
            }catch (e: Exception) {
                Log.e("IFOUND_PET", "ERROR FETCHING URLS cats missing $e")
                _catsMissingResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    fun getDogsMissing(){
        _dogsMissingResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = pawsRepo.getDogsMissing()
                if(response != null && response.isSuccessful){
                    _dogsMissingResult.value = BaseResponse.Success(response.body())
                }else{
                    _dogsMissingResult.value = BaseResponse.Error(response?.message())
                }
            }catch (e: Exception) {
                Log.e("IFOUND_PET", "ERROR FETCHING URLS dog missing $e")
                _dogsMissingResult.value = BaseResponse.Error(e.message)
            }
        }
    }
    fun getRecentlyMissing(){
        _recentlyMissingResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = pawsRepo.getRecentlyAdded()
                if(response != null && response.isSuccessful){
                    _recentlyMissingResult.value = BaseResponse.Success(response.body())
                }else{
                    _recentlyMissingResult.value = BaseResponse.Error(response?.message())
                }
            }catch (e: Exception) {
                _recentlyMissingResult.value = BaseResponse.Error(e.message)
                Log.e("IFOUND_PET", "ERROR FETCHING URLS recently add $e")
            }
        }
    }


    fun foundPet(imagePath:String?,description : String){
        _iFoundPetResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val desc = RequestBody.create("text/plain".toMediaTypeOrNull(),description)
                val petImage :MultipartBody.Part? = if (imagePath != null){
                    val file = File(imagePath)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("petImage",file.name,requestFile)
                }else{
                    null
                }
                val response = pawsRepo.iFoundPet(petImage,desc)
                if (response != null && response.isSuccessful){
                    _iFoundPetResult.value = BaseResponse.Success(response.body())
                }else{
                    _iFoundPetResult.value = BaseResponse.Error(response?.message())
                    Log.e("FOUND_PET", "ERROR FETCHING URLS found pet res ${response?.errorBody()}")
                }
            }catch (e: Exception) {
                Log.e("FOUND_PET", "ERROR FETCHING URLS ifound pet  catch $e")
                _iFoundPetResult.value = BaseResponse.Error(e.message)
            }
        }
    }

    fun getMissingPet(imagePath: String?){
        _missingPetResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val petImage :MultipartBody.Part? = if (imagePath != null){
                    val file = File(imagePath)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("petImage",file.name,requestFile)
                }else{
                    null
                }
                val response = pawsRepo.getMissingPet(petImage)
                if(response != null && response.isSuccessful){
                    _missingPetResult.value = BaseResponse.Success(response.body())
                }else{
                    _missingPetResult.value = BaseResponse.Error(response?.message())
                    Log.e("MISS_PET", "ERROR FETCHING URLS  ${response?.errorBody()}")
                }
            }catch (e: Exception) {
                Log.e("MISS_PET", "ERROR FETCHING URLS Missing Pet catch $e")
                _missingPetResult.value = BaseResponse.Error(e.message)
            }

        }
    }
}