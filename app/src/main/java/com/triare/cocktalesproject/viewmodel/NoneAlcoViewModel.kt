package com.triare.cocktalesproject.viewmodel

import android.app.Application
import com.triare.cocktalesproject.data.api.CocktaleDto
import com.triare.cocktalesproject.data.api.CocktalesRepository

class NoneAlcoViewModel (application: Application): BaseViewModel(application){

    override suspend fun getCocktales(cocktalesRepository: CocktalesRepository): Result<List<CocktaleDto>> {
        return cocktalesRepository.getNonAlco()
     }
    }
