package com.triare.cocktalesproject.data.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlcoSource {
    suspend fun getAlcoholCocktales(): Response<CocktaleResponse> {
        return createAlcoApi().getAlcoList()
    }
    suspend fun getNonAlcoholCocktales():Response<CocktaleResponse>
    {
        return createAlcoApi().getNonAlcoList()
    }
    fun createAlcoApi(): CocktalesApi {
        var BASE_URL = "https://www.thecocktaildb.com/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(CocktalesApi::class.java)


    }

}