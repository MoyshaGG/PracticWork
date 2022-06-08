package com.triare.cocktalesproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.triare.cocktalesproject.model.UserDto

class SplashActivity : AppCompatActivity() {
    companion object {
        val preferences = "sharedPref"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mainIntent = Intent(this, MainActivity::class.java)
        FacebookSdk.fullyInitialize();
        AppEventsLogger.activateApp(application);
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

//        try {
//            FacebookSdk.sdkInitialize(getApplicationContext())
//            AppEventsLogger.activateApp(Application())
//
//            val accessToken: AccessToken = AccessToken.getCurrentAccessToken()!!
//
//            if (!accessToken.isExpired) {
//                startActivity(mainIntent)
//                finish()
//            }
//        } catch (e: ApiException) {
            val sharedPref = getSharedPreferences(preferences, Context.MODE_PRIVATE)
            val accountNamePref = sharedPref.getString(getString(R.string.account_name), "")
            val accountPhotoPref = sharedPref.getString(getString(R.string.account_photo), "")
            Log.i(
                "splashActivityPreference",
                "( sharedPref = ${accountNamePref}), ( sharedName = ${accountPhotoPref})"
            )

            if (accountNamePref.toString() != "" && accountPhotoPref.toString() != "") {
                val userDto = UserDto(accountNamePref.toString(), accountPhotoPref.toString())
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


//}



