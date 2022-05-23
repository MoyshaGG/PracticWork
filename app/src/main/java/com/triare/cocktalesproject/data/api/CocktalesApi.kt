package com.triare.cocktalesproject.data.api

import retrofit2.Response
import retrofit2.http.GET


interface CocktalesApi {
    @GET("api/json/v1/1/filter.php?a=Alcoholic") //  www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
    // @Headers(Content-Type: )
    suspend fun getAlcoList(): Response<CocktaleResponse>

    @GET("api/json/v1/1/filter.php?a=Non_Alcoholic") //  www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
    // @Headers(Content-Type: )
    suspend fun getNonAlcoList(): Response<CocktaleResponse>
}
