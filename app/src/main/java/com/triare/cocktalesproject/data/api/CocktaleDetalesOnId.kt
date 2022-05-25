package com.triare.cocktalesproject.data.api

import com.google.gson.annotations.SerializedName

data class   CocktaleDetalesOnId(val drinks:  List<CocktaleDetailsDto>)

data class CocktaleDetailsDto(
    @SerializedName("strDrink")
    val drink: String,
    @SerializedName("strDrinkThumb")
    val drinkImage: String,
    @SerializedName("idDrink")
    val idDrink: Int,
    @SerializedName("strInstructions")
    val instructions:String,
    @SerializedName("strIngredient1")
    val ingredient1:String,
    @SerializedName("strIngredient2")
    val ingredient2:String,
    @SerializedName("strIngredient3")
    val ingredient3:String,
    @SerializedName("strIngredient4")
    val ingredient4:String,
    @SerializedName("strIngredient5")
    val ingredient5:String,
    @SerializedName("strIngredient6")
    val ingredient6:String




)

