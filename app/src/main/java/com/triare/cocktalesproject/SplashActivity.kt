package com.triare.cocktalesproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        Handler().postDelayed({
            if (user != null) {
                val loginIntent = Intent(this,MainActivity::class.java )
                startActivity(loginIntent)
            }
            else{
                val loginIntent = Intent(this,LoginActivity::class.java )
                startActivity(loginIntent)
            }
        }, 2000)

    }
}