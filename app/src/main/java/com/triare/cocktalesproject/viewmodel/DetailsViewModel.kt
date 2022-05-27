package com.triare.cocktalesproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktaleDetailsDto
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.dvo.CocktaleDetailsDvo
import com.triare.cocktalesproject.dvo.IngredientDvo
import kotlinx.coroutines.launch

class DetailsViewModel(val cocktaleId: Int) : ViewModel() {

    protected val cocktalesRepository = CocktalesRepository()
    val _cocktaleDvoLiveData = MutableLiveData<CocktaleDetailsDvo>()

    init {

        getDrinkById()
    }

    private fun getDrinkById() {
        viewModelScope.launch {

            val response = cocktalesRepository.getCocktaleById(cocktaleId)
            //  delay(1000)

            if (response.isSuccess) {
                val cocktale = response.getOrThrow().drinks[0]
                _cocktaleDvoLiveData.value =
                    CocktaleDetailsDvo(
                        name = cocktale.drink,
                        ingredients = prepareIngredients(cocktale),
                        picture = cocktale.drinkImage, instruction = cocktale.instructions
                    )
            } else {
                _cocktaleDvoLiveData.value = CocktaleDetailsDvo(
                    // response.exceptionOrNull()?.message ?:"SomethingWrong", idDrink = 11007, drinkImage = ""
                    emptyList(), "BadLiveData", "", "BadLiveData"
                )
            }
        }
    }

    private fun prepareIngredients(cocktaleDetalesOnId: CocktaleDetailsDto): List<IngredientDvo> {
        val ingredients = mutableListOf<IngredientDvo>()

       if(!cocktaleDetalesOnId.ingredient1.isNullOrEmpty()) {
           ingredients.add(
               IngredientDvo(
                   cocktaleDetalesOnId.ingredient1,
                   stringMethod(cocktaleDetalesOnId.ingredient1)
               )
           )
       }
        if(!cocktaleDetalesOnId.ingredient2.isNullOrEmpty()) {

            ingredients.add(
                IngredientDvo(
                    cocktaleDetalesOnId.ingredient2,
                    stringMethod(cocktaleDetalesOnId.ingredient2)
                )
            )
        }
        if(!cocktaleDetalesOnId.ingredient3.isNullOrEmpty()) {

            ingredients.add(
                IngredientDvo(
                    cocktaleDetalesOnId.ingredient3,
                    stringMethod(cocktaleDetalesOnId.ingredient3)
                )
            )
        }
        if(!cocktaleDetalesOnId.ingredient4.isNullOrEmpty()) {
            ingredients.add(
                IngredientDvo(
                    cocktaleDetalesOnId.ingredient4,
                    stringMethod(cocktaleDetalesOnId.ingredient4)
                )
            )
        }
        if(!cocktaleDetalesOnId.ingredient5.isNullOrEmpty()) {
            ingredients.add(
                IngredientDvo(
                    cocktaleDetalesOnId.ingredient5,
                    stringMethod(cocktaleDetalesOnId.ingredient5)
                )
            )
        }
        if(!cocktaleDetalesOnId.ingredient6.isNullOrEmpty()) {
            ingredients.add(
                IngredientDvo(
                    cocktaleDetalesOnId.ingredient6,
                    stringMethod(cocktaleDetalesOnId.ingredient6)
                )
            )
        }
        else {

        }
            return ingredients
    }

    private fun stringMethod(ingredient: String): String {
        return "https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"
    }

}


