package com.triare.cocktalesproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.dvo.NonAlcoDvo
import com.triare.cocktalesproject.ui.alco_cocktales.NonAlcoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoneAlcoViewModel (application: Application): AndroidViewModel(application){
    private val cocktalesRepository  = CocktalesRepository() ;
    private val _nonalcoDvoLiveData = MutableLiveData<NonAlcoResult>()
    val NonAlcoDvoLiveData: LiveData<NonAlcoResult> = _nonalcoDvoLiveData


    init {
        getCurrentData()
        //Item.setOnClickListener{}

    }


    private fun getCurrentData() {
        viewModelScope.launch {
            try {
                val response = cocktalesRepository.getNonAlco()

                if (response.isSuccess)
                {
                   _nonalcoDvoLiveData.value = NonAlcoResult(drinks = response.getOrDefault(emptyList()).map
                       {
                        NonAlcoDvo(
                            drink = it.drink,
                            drinkImage = it.drinkImage,
                            idDrink = it.idDrink
                        )
                    })                }
                else{
                    //Result.failure<AlcoResult>())
                    _nonalcoDvoLiveData.value = NonAlcoResult(error = response.exceptionOrNull()?.message ?:"SomethingWrong")
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {}
            }
        }
    }
}
