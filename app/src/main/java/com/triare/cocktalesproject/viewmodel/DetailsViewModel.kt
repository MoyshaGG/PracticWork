package com.triare.cocktalesproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktaleDetalesOnId
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.data.api.Ingredients
import com.triare.cocktalesproject.dvo.CocktaleDetailsDvo
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
                _cocktaleDvoLiveData.value = response.getOrDefault(null)
            }
            else{
                _cocktaleDvoLiveData.value = CocktaleDetalesOnId(
                   // response.exceptionOrNull()?.message ?:"SomethingWrong", idDrink = 11007, drinkImage = ""
                emptyList()
                )
            }


        }
    }
    private fun prepareIngredientImage(){
        viewModelScope.launch {
            val responseIngredient = cocktalesRepository.getCocktaleById()
            if(responseIngredient.isSuccess)
            {
                _cocktaleDvoLiveData.value =




            }
        }
    }


    private fun stringMethod(ingredient: String):String
    {
        return "https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"
    }

}


