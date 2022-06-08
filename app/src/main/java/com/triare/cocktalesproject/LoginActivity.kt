package com.triare.cocktalesproject

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.triare.cocktalesproject.model.UserDto
import java.util.*


class LoginActivity : AppCompatActivity() {
    val callbackManager = CallbackManager.Factory.create()

    companion object {
        val RC_SIGN_IN = 4422
        val  EMAIL = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        FacebookSdk.sdkInitialize(getApplicationContext())
        val callbackManager = CallbackManager.Factory.create()

        AppEventsLogger.activateApp(Application())
        val loginButton: LoginButton = findViewById(R.id.login_facebook_button)
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                    val mainIntent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

        loginButton.setReadPermissions(Arrays.asList(EMAIL))
        loginButton.setOnClickListener()
        {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))



        }


        signInButton.setOnClickListener {
            signIn()
            saveUser()
        }


//        LoginManager.getInstance().registerCallback(callbackManager,
//            object : FacebookCallback<LoginResult> {
//                override fun onSuccess(result: LoginResult) {
//                    val mainIntent = Intent(applicationContext, MainActivity::class.java)
//                    startActivity(mainIntent)
//                    finish()
//                }
//
//                override fun onCancel() {
//                    Toast.makeText(applicationContext,"onCancel", Toast.LENGTH_LONG).show()
//
//                }
//
//                override fun onError(exception: FacebookException) {
//                    Toast.makeText(applicationContext,"onError", Toast.LENGTH_LONG).show()
//
//                }
//            })


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }



    fun saveUser() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        var account: GoogleSignInAccount? = null
        account = GoogleSignIn.getLastSignedInAccount(this)
        with(sharedPref.edit()) {
            putString(getString(R.string.account_name), account?.displayName.toString())
            putString(getString(R.string.account_photo), account?.photoUrl.toString())
            apply()
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    fun saveUser(name:String,photo:String) {
        val sharedPref = getSharedPreferences(SplashActivity.preferences,Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.account_name), name)
            putString(getString(R.string.account_photo), photo)
            apply()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode,resultCode,data)
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
//        }
//    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {

            val mainIntent = Intent(this, MainActivity::class.java)
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            saveUser(account.displayName.toString(), account.photoUrl.toString())
            val userDto = UserDto(account.displayName.toString(), account.photoUrl.toString())
            mainIntent.putExtra("user", userDto)
            startActivity(mainIntent)
            finish()

        } catch (e: ApiException) {
        }
    }
}

