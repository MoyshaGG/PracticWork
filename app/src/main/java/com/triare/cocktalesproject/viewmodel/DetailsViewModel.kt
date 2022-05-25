package com.triare.cocktalesproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktaleDetalesOnId
import com.triare.cocktalesproject.data.api.CocktalesRepository
import kotlinx.coroutines.launch

class DetailsViewModel(val cocktaleId: Int) : ViewModel() {

    protected val cocktalesRepository = CocktalesRepository()
    val _alcoDvoLiveData = MutableLiveData<CocktaleDetalesOnId>()


    init {

        responseIdDrink()
    }

    private fun responseIdDrink() {
        viewModelScope.launch {

        val response = cocktalesRepository.getIdCocktales(cocktaleId)
          //  delay(1000)
            if (response.isSuccess)
            {
                _alcoDvoLiveData.value = response.getOrDefault(null)
            }
            else{
                _alcoDvoLiveData.value = CocktaleDetalesOnId(
                   // response.exceptionOrNull()?.message ?:"SomethingWrong", idDrink = 11007, drinkImage = ""
                emptyList()
                )
            }


        }
    }

}


