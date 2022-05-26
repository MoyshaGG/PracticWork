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

            if (response.isSuccess)
            {
                val cocktale = response.getOrThrow().drinks[0]
//                val ingredientsDvo:List<IngredientDvo> = cocktale.ingredient1,
//                    cocktale.ingredient2,cocktale.ingredient3, cocktale.ingredient4,
//                    cocktale.ingredient5,cocktale.ingredient6)
              //  IngredientDvo[0] = "","";



                _cocktaleDvoLiveData.value =
                    CocktaleDetailsDvo(name = cocktale.drink,
                        ingredients = prepareIngredients(cocktale) ,
                        picture = cocktale.drinkImage , instruction = cocktale.instructions)
                    //response.getOrDefault(null)
            }
            else{
                _cocktaleDvoLiveData.value = CocktaleDetailsDvo(
                   // response.exceptionOrNull()?.message ?:"SomethingWrong", idDrink = 11007, drinkImage = ""
                emptyList(), "","","")
            }


        }
    }

    private fun prepareIngredients(cocktaleDetalesOnId: CocktaleDetailsDto): List<IngredientDvo> {
    val ingredients = mutableListOf<IngredientDvo>()
        if()
        ingredients.add()

      return ingredients
    }
    private fun stringMethod(ingredient: String):String
    {
        return "https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"
    }

}


