package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.triare.cocktalesproject.databinding.ActivityMainBinding
import com.triare.cocktalesproject.model.UserDto
import org.json.JSONObject

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

        val accessToken: AccessToken = AccessToken.getCurrentAccessToken()!!
        val request = GraphRequest.newMeRequest(
            accessToken, object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(
                    obj: JSONObject?,
                    response: GraphResponse?
                ) {
                    val fullname = obj?.getString("name")
                    val url:String = obj?.getJSONObject("picture")?.getJSONObject("data")?.getString("url").toString()
                    Glide.with(applicationContext).load(url).error(R.drawable.ic_launcher_background)
                        .into(binding.iconUser)
                    binding.usernameView.setText(fullname)
                }
            })
        val parameters = Bundle()
        parameters.putString("fields", "id,name,link,picture")
        request.parameters = parameters
        request.executeAsync()
        binding.usernameView.text = "Welcome,  " + user?.name + ".  -_(*_*)_-"
        Glide.with(this).load(user?.photo.toString()).error(R.drawable.ic_launcher_background)
            .into(binding.iconUser)
        val exitButton = binding.exitView

        exitButton.setOnClickListener{
            val loginIntent = Intent(this,LoginActivity::class.java )
            signOut()
            LoginManager.getInstance().logOut()
            startActivity(loginIntent)
            finish()
        }

    }
    private fun signOut() {
        val sharedPref =
            getSharedPreferences(SplashActivity.preferences, Context.MODE_PRIVATE) ?: return
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
    private fun getUserInfo(): UserDto? {
        val arguments = intent.extras
        return arguments?.getParcelable<UserDto>("user")
    }
}


