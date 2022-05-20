package com.triare.cocktalesproject.data.api

//class NoAlcoCocktalesRepository(private val nonAlcoSource: NonAlcoSource = NonAlcoSource())
//{
//    suspend fun getNonAlco(): Result<List<NonAlcoDto>> {
//        val response = nonAlcoSource.getNonAlcoholCocktales()
//        return if (response.isSuccessful) {
//            Result.success(response.body()!!.drinks)
//        } else {
//            Result.failure(Throwable(response.message()))
//        }
//    }
//}