package com.example.domain.pet

import okhttp3.MultipartBody

data class AddPetData(
    val petImage: MultipartBody.Part,
    val name:String? = null,
    val type:String? = null,
    val birthday:String? = null,
    val category:String? = null,
    val gender:String? = null,
    val profileBio:String? = null,
    val weight:String? = null,
    val adoptionDay:String? = null,
    val size:String? = null,
    )
