package com.triare.cocktalesproject.viewmodel

//
//abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
//
//     val cocktalesRepository = CocktalesRepository()
//     val _alcoDvoLiveData = MutableLiveData<AlcoResult>()
//    val alcoDvoLiveData: LiveData<AlcoResult> = _alcoDvoLiveData
//
//    init {
//        getCurrentData(response : Result<List<T>>)
//        //Item.setOnClickListener{}
//
//    }
//    private fun getCurrentData(response : Result<List<T>>) {
//        viewModelScope.launch {
//            try {
//                val response = cocktalesRepository.getAlco()
//                if (response.isSuccess)
//                {
//                    _alcoDvoLiveData.value = AlcoResult(drinks = response.getOrDefault(emptyList()).map {
//                        AlcoDvo(
//                            drink = it.drink,
//                            drinkImage = it.drinkImage,
//                            idDrink = it.idDrink
//                        )
//                    })                }
//                else{
//                    //Result.failure<AlcoResult>())
//                    _alcoDvoLiveData.value = AlcoResult(error = response.exceptionOrNull()?.message ?:"SomethingWrong")
//                }
//            }
//            catch (e: Exception) {
//                withContext(Dispatchers.Main) {}
//            }
//        }
//    }
//    abstract fun getResponseAlco(cocktalesRepository: CocktalesRepository)
//
//
//}