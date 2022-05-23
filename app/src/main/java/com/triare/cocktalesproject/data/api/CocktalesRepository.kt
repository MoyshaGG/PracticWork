package com.triare.cocktalesproject.data.api

class CocktalesRepository(private val alcoSource: AlcoSource = AlcoSource()) {


    suspend fun getAlco(): Result<List<CocktaleDto>> {
        val response = alcoSource.getAlcoholCocktales()
        return if (response.isSuccessful) {

            Result.success(response.body()!!.drinks)

        } else {
            Result.failure(Throwable(response.message()))
        }

    }
    suspend fun getNonAlco(): Result<List<CocktaleDto>> {
        val response = alcoSource.getNonAlcoholCocktales()
        return if (response.isSuccessful) {

            Result.success(response.body()!!.drinks)

        } else {
            Result.failure(Throwable(response.message()))
        }

    }
}
