package com.triare.cocktalesproject.dvo

data class CocktaleDetailsDvo(
    val ingredients: List<IngredientDvo>,
    val name: String,
    val picture: String,
    val instruction: String
)
