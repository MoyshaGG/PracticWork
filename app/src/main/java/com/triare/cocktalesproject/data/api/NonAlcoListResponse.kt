package com.triare.cocktalesproject.data.api

import com.google.gson.annotations.SerializedName

data class NonAlcoListResponse(val drinks: List<CocktaleDto>)


data class CocktaleDto(
    @SerializedName("strDrink")
    val drink: String,
    @SerializedName("strDrinkThumb")
    val drinkImage: String,
    @SerializedName("idDrink")
    val idDrink: Int
)