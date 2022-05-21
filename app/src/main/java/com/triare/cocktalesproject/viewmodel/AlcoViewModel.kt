package com.triare.cocktalesproject.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.dvo.AlcoDvo
import com.triare.cocktalesproject.ui.alco_cocktales.AlcoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlcoViewModel(application: Application) : BaseViewModel(application) {
    init {
        getCurrentData()
        //Item.setOnClickListener{}

    }
    private fun getCurrentData() {

        viewModelScope.launch {
            try {
                val response = cocktalesRepository.getAlco()
                if (response.isSuccess)
                {
                    _alcoDvoLiveData.value = AlcoResult(drinks = response.getOrDefault(emptyList()).map {
                        AlcoDvo(
                            drink = it.drink,
                            drinkImage = it.drinkImage,
                            idDrink = it.idDrink
                        )
                    })                }
                    else{
                        //Result.failure<AlcoResult>())
                        _alcoDvoLiveData.value = AlcoResult(error = response.exceptionOrNull()?.message ?:"SomethingWrong")
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {}
            }
        }
    }

    override fun getResponseAlco(cocktalesRepository: CocktalesRepository) {
        return cocktalesRepository.getAlco()
    }
}
