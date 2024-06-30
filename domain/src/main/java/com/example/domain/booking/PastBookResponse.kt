package com.example.domain.booking


import com.google.gson.annotations.SerializedName

data class PastBookResponse(
    @SerializedName("request")
    val request: List<Request>
) {
    data class Request(
        @SerializedName("cardExpireDate")
        val cardExpireDate: String?,
        @SerializedName("cardNumber")
        val cardNumber: String?,
        @SerializedName("cardSecurityCode")
        val cardSecurityCode: String?,
        @SerializedName("completed")
        val completed: Boolean?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("date")
        val date: String?,
        @SerializedName("duration")
        val duration: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("location")
        val location: List<String?>?,
        @SerializedName("notes")
        val notes: String?,
        @SerializedName("number")
        val number: String?,
        @SerializedName("payment")
        val payment: String?,
        @SerializedName("petsId")
        val petsId: List<String?>?,
        @SerializedName("petsNumber")
        val petsNumber: Any?,
        @SerializedName("pickUp")
        val pickUp: Boolean?,
        @SerializedName("remindMe3Hours")
        val remindMe3Hours: Boolean?,
        @SerializedName("requestTotalPrice")
        val requestTotalPrice: Int?,
        @SerializedName("saveCard")
        val saveCard: Boolean?,
        @SerializedName("servicePrice")
        val servicePrice: Int?,
        @SerializedName("serviceType")
        val serviceType: String?,
        @SerializedName("time")
        val time: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("userId")
        val userId: String?,
        @SerializedName("__v")
        val v: Int?
    )
}