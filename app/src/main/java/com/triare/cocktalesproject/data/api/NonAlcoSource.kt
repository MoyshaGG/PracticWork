package com.triare.cocktalesproject.data.api

//class NonAlcoSource {
//    suspend fun getNonAlcoholCocktales(): Response<NonAlcoListResponse> {
//        return createAlcoApi().getNoneAlcoList()
//
//    }
//    fun createAlcoApi(): NonAlcoApi {
//        var BASE_URL = "https://www.thecocktaildb.com/"
//        // www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
//         val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//        return retrofit.create(NonAlcoApi::class.java)
//    }
//}