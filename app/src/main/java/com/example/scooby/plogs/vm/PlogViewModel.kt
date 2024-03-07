package com.example.scooby.plogs.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scooby.plogs.repo.ScoobyRepository
import com.example.scooby.plogs.model.Plogs
import kotlinx.coroutines.launch
import retrofit2.Response

class PlogViewModel (private val scoobyRepository : ScoobyRepository): ViewModel() {

    private val _plogs = MutableLiveData<Response<List<Plogs>>>()
    val plogs:LiveData<Response<List<Plogs>>> get() = _plogs

    fun getPlogs(){
        viewModelScope.launch {
            _plogs.value = scoobyRepository.getAllPlogs()
        }
    }

}