package com.triare.cocktalesproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.triare.cocktalesproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
  //  private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)

      val view = binding.root
      setContentView(view)
        initNavigation()

    }
    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)

        binding.navView.apply {
            setupWithNavController(navController)
        }
    }
}


