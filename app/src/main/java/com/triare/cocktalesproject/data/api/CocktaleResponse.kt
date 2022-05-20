package com.triare.cocktalesproject.data.api

import com.google.gson.annotations.SerializedName


data class CocktaleResponse(val drinks: List<AlcoDto>)


data class AlcoDto(
    @SerializedName("strDrink")
    val drink: String,
    @SerializedName("strDrinkThumb")
    val drinkImage: String,
    @SerializedName("idDrink")
    val idDrink: Int
)

