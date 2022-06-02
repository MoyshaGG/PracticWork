package com.triare.cocktalesproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.triare.cocktalesproject.model.UserDto

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
                val loginIntent = Intent(this,LoginActivity::class.java )
                startActivity(loginIntent)

        var account: GoogleSignInAccount? = null
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val accountName =  sharedPref.getString(getString(R.string.account_name), account?.displayName.toString())
        val accountPhoto =  sharedPref.getString(getString(R.string.account_photo), account?.photoUrl.toString())

        Log.i("UserInfo", "username =${account?.displayName},email =${account?.email},picture${account?.photoUrl}")
        //  if (account != null && getExit() != "exit") {
        if(accountName != null ){
//            val userInfo: String = account?.displayName.toString()
//            val photoUrl: String = account?.photoUrl.toString()
            val userDto: UserDto
            userDto = UserDto(accountName.toString(), accountPhoto.toString())
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("user", userDto)
            startActivity(mainIntent)
            Log.i("UserInfo", "username =${account?.displayName},email =${account?.email},picture${account?.photoUrl}")
        }
        else{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}