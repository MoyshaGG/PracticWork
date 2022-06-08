package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import com.triare.cocktalesproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val preferences = "sharedPref"

    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNavigation()
       // val user = getUserInfo()
        supportActionBar?.hide()

        val sharedPref = getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val accountNamePref = sharedPref.getString(getString(R.string.account_name), "")
        val accountPhotoPref = sharedPref.getString(getString(R.string.account_photo), "")
        Log.i(
            "BEB MainActivityPreferences",
            "( sharedPref = ${accountNamePref}), ( sharedName = ${accountPhotoPref})"
        )

        binding.usernameView.text = "Welcome,  $accountNamePref"
        Glide.with(this).load(accountPhotoPref).error(R.drawable.donwloadimage)
            .into(binding.iconUser)
        val exitButton = binding.exitView

        exitButton.setOnClickListener{
            val loginIntent = Intent(this,LoginActivity::class.java )
            signOut()
            LoginManager.getInstance().logOut()
            Log.i(
                "BEB EXIT",
                "( sharedPref = ${accountNamePref}), ( sharedName = ${accountPhotoPref})"
            )
            startActivity(loginIntent)
            finish()
        }

    }
    private fun signOut() {
        val sharedPref =
            getSharedPreferences(preferences, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.account_name), null)
            putString(getString(R.string.account_photo), null)
            apply()
        }
    }
    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.apply {
            setupWithNavController(navController)
        }
    }
//    private fun getUserInfo(): UserDto? {
//        val arguments = intent.extras
//        return arguments?.getParcelable<UserDto>("user")
//    }
}


