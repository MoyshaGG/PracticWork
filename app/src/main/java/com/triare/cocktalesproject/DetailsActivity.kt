package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.databinding.ActivityDetailsBinding
import com.triare.cocktalesproject.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        viewModel = DetailsViewModel(getId())





        viewModel._alcoDvoLiveData.observe(this) {
            binding.nameAlcohol.text = it.drinks[0].drink
            binding.descriptionAlcohol.text = "    "+it.drinks[0].instructions
            Glide.with(this).load(it.drinks[0].drinkImage).error(R.drawable.ic_launcher_background)
                .into(binding.imageAlcohol)

           Glide.with(this).load(stringMethod(it.drinks[0].ingredient1))
            it.drinks[0].ingredient1

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
