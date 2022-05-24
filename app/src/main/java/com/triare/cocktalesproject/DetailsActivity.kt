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
        val descriptionAlcohol:TextView = findViewById(R.id.descriptionAlcohol)
        viewModel._alcoDvoLiveData.observe(this) {
            descriptionAlcohol.text = it.drink
            Glide.with(this).load(it.drinkImage).error(R.drawable.ic_launcher_background)
                .into(imageAlcohol)
        }

    }
    private fun getId():Int{
        val arguments = intent.extras
        return arguments?.getInt("idDrink")!!.toInt()
    }

}
