package com.triare.cocktalesproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.triare.cocktalesproject.databinding.ActivityMainBinding
import com.triare.cocktalesproject.model.UserDto

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)
                initNavigation()
        getUserInfo()
        title = "Welcome,  "+getUserInfo() +".  -_(*_*)_-"

    }
    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.apply {
            setupWithNavController(navController)
        }
    }
    private fun getUserInfo(): UserDto? {
        val arguments = intent.extras
        return arguments?.getParcelable<UserDto>("user")

    }

}


