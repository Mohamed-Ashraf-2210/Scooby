package com.example.domain.pet

data class AddPetData(
    val name: String,
    val type: String,
    val birthday: String,
    val breed: String,
    val gender: String,
    val profileBio: String,
    val adoptionDay: String,
    val size: String,
)