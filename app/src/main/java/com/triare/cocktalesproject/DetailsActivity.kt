package com.triare.cocktalesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.triare.cocktalesproject.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        DetailsViewModel(cocktaleId = getId())
    }
    private fun getId():Int{
        val arguments = intent.extras
        return arguments?.getInt("idDrink")!!.toInt()
    }

}
