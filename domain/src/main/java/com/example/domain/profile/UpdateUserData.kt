package com.example.domain.profile

import java.io.File

data class UpdateUserData(
    var imageUri: File,
    var name: String,
    val email: String
)
