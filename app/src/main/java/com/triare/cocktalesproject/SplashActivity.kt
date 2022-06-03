package com.triare.cocktalesproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.triare.cocktalesproject.model.UserDto

class SplashActivity : AppCompatActivity() {
    companion object {
        val preferences = "sharedPref"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var account: GoogleSignInAccount? = null
        val sharedPref = getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val accountNamePref = sharedPref.getString(getString(R.string.account_name), "")
        val accountPhotoPref = sharedPref.getString(getString(R.string.account_photo), "")
        Log.i(
            "splashActivityPreference",
            "( sharedPref = ${accountNamePref}), ( sharedName = ${accountPhotoPref})"
        )
        //  if (account != null && getExit() != "exit") {
        if (accountNamePref.toString() != "" && accountPhotoPref.toString() != "") {
            val userDto = UserDto(accountNamePref.toString(), accountPhotoPref.toString())
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("user", userDto)
            startActivity(mainIntent)
            finish()

        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

    }
}