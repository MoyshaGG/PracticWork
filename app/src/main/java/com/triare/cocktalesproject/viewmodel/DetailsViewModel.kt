package com.triare.cocktalesproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triare.cocktalesproject.data.api.CocktalesRepository
import com.triare.cocktalesproject.ui.alco_cocktales.AlcoResult
import kotlinx.coroutines.launch

class DetailsViewModel(val cocktaleId: Int) : ViewModel() {

    protected val cocktalesRepository = CocktalesRepository()
    val _alcoDvoLiveData = MutableLiveData<AlcoResult>()
    val alcoDvoLiveData: LiveData<AlcoResult> = _alcoDvoLiveData

    init {
        responseIdDrink()
    }

    private fun responseIdDrink() {
        viewModelScope.launch {
        val response = cocktalesRepository

        }
    }

}


