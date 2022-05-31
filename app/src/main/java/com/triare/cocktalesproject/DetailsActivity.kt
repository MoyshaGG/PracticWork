package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.cocktalesproject.adapters.IngredientAdapter
import com.triare.cocktalesproject.databinding.ActivityDetailsBinding
import com.triare.cocktalesproject.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var ingredientAdapter: IngredientAdapter

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = DetailsViewModel(getId())
        val recyclerView: RecyclerView = binding.recycleViewIngredients
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        ingredientAdapter = IngredientAdapter(applicationContext)
        recyclerView.adapter = ingredientAdapter
        viewModel._cocktaleDvoLiveData.observe(this) {

            title = it.name
            // binding.nameAlcohol.text = it.name
            binding.descriptionAlcohol.text = "    " + it.instruction
            Glide.with(this).load(it.picture).error(R.drawable.ic_launcher_background)
                .into(binding.imageAlcohol)

            ingredientAdapter.setDataList(it.ingredients)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getId(): Int {
        val arguments = intent.extras
        return arguments?.getInt("idDrink")!!.toInt()
    }
}
