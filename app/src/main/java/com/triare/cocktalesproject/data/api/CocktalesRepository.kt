package com.triare.cocktalesproject.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktalesRepository() {

    fun createAlcoApi(): CocktalesApi {
        var BASE_URL = "https://www.thecocktaildb.com/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(CocktalesApi::class.java)
    }

    suspend fun getAlco(): Result<List<CocktaleDto>> {
        val response = createAlcoApi().getAlcoList()
        return if (response.isSuccessful) {

            Result.success(response.body()!!.drinks)

        } else {
            Result.failure(Throwable(response.message()))
        }

    }

    suspend fun getNonAlco(): Result<List<CocktaleDto>> {
        val response = createAlcoApi().getNonAlcoList()
        return if (response.isSuccessful) {

            Result.success(response.body()!!.drinks)

        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun getCocktaleById(id: Int): Result<CocktaleDetalesOnId> {
            val response = createAlcoApi().getCocktaleDetails(id)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable(response.message()))
        }
    }


//    suspend fun getNameCocktales(drink:String):Result<CocktaleInstructionImageDto>
//    {
//    }
}
