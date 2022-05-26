package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.adapters.IngredientAdapter
import com.triare.cocktalesproject.data.api.CocktaleDetailsDto
import com.triare.cocktalesproject.databinding.ActivityDetailsBinding
import com.triare.cocktalesproject.databinding.ItemIngredientsBinding
import com.triare.cocktalesproject.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var bindingItem: ItemIngredientsBinding

    private lateinit var viewModel: DetailsViewModel
    private lateinit var  ingredientAdapter: IngredientAdapter
    private var dataList = mutableListOf<CocktaleDetailsDto>()
    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        bindingItem = ItemIngredientsBinding.inflate(layoutInflater)
        viewModel = DetailsViewModel(getId())
        val view = binding.root
        setContentView(view)
        /////////////
        val recyclerView: RecyclerView = binding.recycleViewIngredients
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        ingredientAdapter = IngredientAdapter(applicationContext)
        recyclerView.adapter = ingredientAdapter
        // photoAdapter.setDataList(dataList = IngredientDvo)
        ////////////
        viewModel._cocktaleDvoLiveData.observe(this) {

            binding.nameAlcohol.text = it.name
            binding.descriptionAlcohol.text = "    "+it.instruction
            Glide.with(this).load(it.picture).error(R.drawable.ic_launcher_background)
                .into(binding.imageAlcohol)
//            dataList.add(CocktaleDetailsDto(it.drinks[0].drink,
//                it.drinks[0].drinkImage,it.drinks[0].idDrink,it.drinks[0].instructions,it.drinks[0].ingredient1,
//                it.drinks[0].ingredient2,it.drinks[0].ingredient3,it.drinks[0].ingredient4,
//                it.drinks[0].ingredient5,it.drinks[0].ingredient6))

  Glide.with(this).load(stringMethod(it.picture)).into(bindingItem.imageIngredientsRecycle)
        }

    }
    private fun stringMethod(ingredient: String):String
    {
        return "https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"
    }
    private fun getId():Int{
        val arguments = intent.extras
        return arguments?.getInt("idDrink")!!.toInt()
    }
}
