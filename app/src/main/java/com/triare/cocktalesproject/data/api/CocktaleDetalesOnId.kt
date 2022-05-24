package com.triare.cocktalesproject.data.api

import com.google.gson.annotations.SerializedName

data class CocktaleDetalesOnId(val drink:String,val drinkImage:String,val idDrink:Int)

data class CocktaleDetailsDto(
    @SerializedName("strDrink")
    val drink: String,
    @SerializedName("strDrinkThumb")
    val drinkImage: String,
    @SerializedName("idDrink")
    val idDrink: Int
)

