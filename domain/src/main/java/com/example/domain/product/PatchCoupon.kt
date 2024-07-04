package com.example.domain.product


import com.google.gson.annotations.SerializedName

data class PatchCoupon(
    @SerializedName("coupon")
    val coupon: String?
)