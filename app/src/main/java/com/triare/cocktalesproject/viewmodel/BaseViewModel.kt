package com.triare.cocktalesproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktaleDto
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.dvo.CocktaleDvo
import com.triare.cocktalesproject.ui.alco_cocktales.AlcoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val cocktalesRepository = CocktalesRepository()
     private val _alcoDvoLiveData = MutableLiveData<AlcoResult>()
    val alcoDvoLiveData: LiveData<AlcoResult> = _alcoDvoLiveData

    init {
        getCurrentData()
    }
    private fun getCurrentData() {

        viewModelScope.launch {
            try {
                val response = getCocktales(cocktalesRepository)
                if (response.isSuccess)
                {
                    _alcoDvoLiveData.value = AlcoResult(drinks = response.getOrDefault(emptyList()).map {
                        CocktaleDvo(
                            drink = it.drink,
                            drinkImage = it.drinkImage,
                            idDrink = it.idDrink
                        )
                    })                }
                else{
                    _alcoDvoLiveData.value = AlcoResult(error = response.exceptionOrNull()?.message ?:"SomethingWrong")
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {}
            }
        }
    }
    abstract suspend fun getCocktales(cocktalesRepository: CocktalesRepository):Result<List<CocktaleDto>>
}