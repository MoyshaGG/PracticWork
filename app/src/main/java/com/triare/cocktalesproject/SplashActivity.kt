package com.triare.cocktalesproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger

class SplashActivity : AppCompatActivity() {
    companion object {
        const val preferences = "sharedPref"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val mainIntent = Intent(this, MainActivity::class.java)
        FacebookSdk.fullyInitialize();
        AppEventsLogger.activateApp(application);
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        val sharedPref = getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val accountNamePref = sharedPref.getString(getString(R.string.account_name), "")
        val accountPhotoPref = sharedPref.getString(getString(R.string.account_photo), "")
        Log.i(
            "BEB SPLASH",
            "( sharedPref = ${accountNamePref}), ( sharedName = ${accountPhotoPref})"
        )
        Handler().postDelayed({
            if (accountNamePref.toString() != "" && accountPhotoPref.toString() != "") {
                startActivity(mainIntent)
                finish()
            } else {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }, 2000)
    }
}




