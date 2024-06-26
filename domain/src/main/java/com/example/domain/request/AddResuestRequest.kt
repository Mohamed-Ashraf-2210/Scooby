package com.example.domain.request


import com.google.gson.annotations.SerializedName

data class AddResuestRequest(
    @SerializedName("cardExpireDate")
    val cardExpireDate: String,
    @SerializedName("cardNumber")
    val cardNumber: String,
    @SerializedName("cardSecurityCode")
    val cardSecurityCode: String,
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("country")
    val country: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("location")
    val location: List<String>,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("payment")
    val payment: String,
    @SerializedName("petsId")
    val petsId: List<String>,
    @SerializedName("petsNumber")
    val petsNumber: String,
    @SerializedName("pickUp")
    val pickUp: Boolean,
    @SerializedName("remindMe3Hours")
    val remindMe3Hours: Boolean,
    @SerializedName("requestTotalPrice")
    val requestTotalPrice: Int,
    @SerializedName("saveCard")
    val saveCard: Boolean,
    @SerializedName("servicePrice")
    val servicePrice: Int,
    @SerializedName("serviceType")
    val serviceType: String,
    @SerializedName("time")
    val time: String
)