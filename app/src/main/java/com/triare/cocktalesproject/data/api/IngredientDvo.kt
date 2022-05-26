package com.triare.cocktalesproject.data.api

data class   Ingredients(val ingredient: IngredientDvo)

data class IngredientDvo(
      val name: String,
      val picture:String
)
