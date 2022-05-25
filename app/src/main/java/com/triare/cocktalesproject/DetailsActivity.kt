package com.triare.cocktalesproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel = DetailsViewModel(getId())
        val imageAlcohol: ImageView = findViewById(R.id.imageAlcohol)
        val nameAlcohol:TextView = findViewById(R.id.nameAlcohol)
        val descriptionAlco:TextView = findViewById(R.id.descriptionAlcohol)

        val ingredientName1 :TextView = findViewById(R.id.ingredientText1)
        val ingredientName2 :TextView = findViewById(R.id.ingredientText2)
        val ingredientName3 :TextView = findViewById(R.id.ingredientText3)
        val ingredientName4 :TextView = findViewById(R.id.ingredientText4)
        val ingredientName5 :TextView = findViewById(R.id.ingredientText5)
        val ingredientName6 :TextView = findViewById(R.id.ingredientText6)

        val ingredientImage1 :ImageView = findViewById(R.id.ingredientImage1)
        val ingredientImage2 :ImageView = findViewById(R.id.ingredientImage2)
        val ingredientImage3 :ImageView = findViewById(R.id.ingredientImage3)
        val ingredientImage4 :ImageView = findViewById(R.id.ingredientImage4)
        val ingredientImage5 :ImageView = findViewById(R.id.ingredientImage5)
        val ingredientImage6 :ImageView = findViewById(R.id.ingredientImage6)

        viewModel._alcoDvoLiveData.observe(this) {
            nameAlcohol.text = it.drinks[0].drink
            descriptionAlco.text = "    "+it.drinks[0].instructions
            Glide.with(this).load(it.drinks[0].drinkImage).error(R.drawable.ic_launcher_background)
                .into(imageAlcohol)

       //     Glide.with(this).load(it.drinks[0].ingredient1).into()



            ingredientName1.text = it.drinks[0].ingredient1
            ingredientName2.text = it.drinks[0].ingredient2
            ingredientName3.text = it.drinks[0].ingredient3
            ingredientName4.text = it.drinks[0].ingredient4
            ingredientName5.text = it.drinks[0].ingredient5
            ingredientName6.text = it.drinks[0].ingredient6



        }

    }
    private fun getId():Int{
        val arguments = intent.extras
        return arguments?.getInt("idDrink")!!.toInt()
    }

}
