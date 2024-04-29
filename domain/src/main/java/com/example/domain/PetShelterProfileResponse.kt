package com.example.domain


import com.google.gson.annotations.SerializedName

class PetShelterProfileResponse : ArrayList<PetShelterProfileResponse.PetShelterProfileResponseItem>(){
    data class PetShelterProfileResponseItem(
        @SerializedName("adoptionDay")
        val adoptionDay: String?,
        @SerializedName("availableForAdoption")
        val availableForAdoption: Boolean?,
        @SerializedName("birthday")
        val birthday: String?,
        @SerializedName("category")
        val category: String?,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("owner")
        val owner: String?,
        @SerializedName("petImage")
        val petImage: String?,
        @SerializedName("profileBio")
        val profileBio: String?,
        @SerializedName("shelterInfo")
        val shelterInfo: String?,
        @SerializedName("size")
        val size: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("vaccinations_id")
        val vaccinationsId: List<Any?>?,
        @SerializedName("weight")
        val weight: Int?
    )
}