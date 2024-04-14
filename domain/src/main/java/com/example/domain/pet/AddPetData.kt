package com.example.domain.pet

import okhttp3.MultipartBody

data class AddPetData(
    val petImage: MultipartBody.Part,
    val name: String,
    val type: String,
    val birthday: String,
    val breed: String,
    val gender: String,
    val profileBio: String,
    val adoptionDay: String,
    val size: String,
)
