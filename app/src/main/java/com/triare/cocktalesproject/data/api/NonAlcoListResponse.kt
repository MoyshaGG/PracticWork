package com.triare.cocktalesproject.data.api

import com.google.gson.annotations.SerializedName

data class NonAlcoListResponse(val drinks: List<NonAlcoDto>)


data class NonAlcoDto(
    @SerializedName("strDrink")
    val drink: String,
    @SerializedName("strDrinkThumb")
    val drinkImage: String,
    @SerializedName("idDrink")
    val idDrink: Int
)