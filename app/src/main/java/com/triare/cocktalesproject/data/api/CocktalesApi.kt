package com.triare.cocktalesproject.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktalesApi {
    @GET("api/json/v1/1/filter.php?a=Alcoholic") //  www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
    // @Headers(Content-Type: )
    suspend fun getAlcoList(): Response<CocktaleResponse>

    @GET("api/json/v1/1/filter.php?a=Non_Alcoholic") //  www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
    // @Headers(Content-Type: )
    suspend fun getNonAlcoList(): Response<CocktaleResponse>

    @GET ( "api/json/v1/1/lookup.php") //  www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    suspend fun getCocktaleDetails  (@Query("i")id:Int): Response<CocktaleDetalesOnId>

    @GET ( "api/json/v1/1/lookup.php") //  www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    suspend fun getIngredients(): Response<Ingredients>


// need to be https://www.thecocktaildb.com/images/ingredients/Lime%20juice-Medium.png

}
