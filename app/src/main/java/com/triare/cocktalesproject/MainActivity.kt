package com.triare.cocktalesproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.triare.cocktalesproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)
        title = "Cocktail List :D"
                initNavigation()
        // throw RuntimeException("Test Crash") // Force a crash for Firebase

        //Firebase.auth.signOut()
    }
    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.apply {
            setupWithNavController(navController)
        }
    }
}


