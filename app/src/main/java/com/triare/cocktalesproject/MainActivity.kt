package com.triare.cocktalesproject


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.triare.cocktalesproject.databinding.ActivityMainBinding
import com.triare.cocktalesproject.model.UserDto

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNavigation()
        val user = getUserInfo()
        supportActionBar?.hide()

        binding.usernameView.text = "Welcome,  " + user?.name + ".  -_(*_*)_-"

        Glide.with(this).load(user?.photo.toString()).error(R.drawable.ic_launcher_background)
            .into(binding.iconUser)
        val exitButton = binding.exitView

        exitButton.setOnClickListener{
            val loginIntent = Intent(this,LoginActivity::class.java )
            val exitVal = "exit"
            loginIntent.putExtra("exit",exitVal)
            startActivity(loginIntent)

            signOut()
        }
    }
    private fun SharedPrefData()
    {
    }
    private fun signOut()
    {
        FirebaseAuth.getInstance().signOut()
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




