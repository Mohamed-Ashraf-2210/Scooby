package com.example.domain.paws.rescue


import com.google.gson.annotations.SerializedName

data class PetsInShelterResponse(
    @SerializedName("petsInShelters")
    val petsInShelters: List<PetsInShelter>
) {
    data class PetsInShelter(
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
        @SerializedName("_id")
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
        val shelterInfo: ShelterInfo?,
        @SerializedName("size")
        val size: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("vaccinations_id")
        val vaccinationsId: List<Any?>?,
        @SerializedName("weight")
        val weight: Int?
    ) {
        data class ShelterInfo(
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("locations")
            val locations: Locations?,
            @SerializedName("numberOfRates")
            val numberOfRates: Int?,
            @SerializedName("pets_Id")
            val petsId: List<String?>?,
            @SerializedName("rate")
            val rate: Double?,
            @SerializedName("shelterImage")
            val shelterImage: String?,
            @SerializedName("shelterName")
            val shelterName: String?,
            @SerializedName("shelterNumber")
            val shelterNumber: String?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("__v")
            val v: Int?
        ) {
            data class Locations(
                @SerializedName("address")
                val address: String?,
                @SerializedName("coordinates")
                val coordinates: List<Double?>?,
                @SerializedName("type")
                val type: String?
            )
        }
    }
}