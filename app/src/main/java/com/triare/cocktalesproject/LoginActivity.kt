package com.triare.cocktalesproject

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
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
        const val preferences = "sharedPref"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        FacebookSdk.sdkInitialize(getApplicationContext())
        val callbackManager = CallbackManager.Factory.create()

        AppEventsLogger.activateApp(Application())
        val loginButton: LoginButton = findViewById(R.id.login_facebook_button)
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                val mainIntent = Intent(applicationContext, MainActivity::class.java)
                val accessToken: AccessToken = AccessToken.getCurrentAccessToken()!!
                val request = GraphRequest.newMeRequest(
                    accessToken
                ) { obj, response ->
                    val fullname = obj?.getString("name").toString()
                    val url = obj?.getJSONObject("picture")?.getJSONObject("data")?.getString("url")
                        .toString()
                    saveUser(fullname, url)
                    Log.i("BEB loginActivity", "( name = ${fullname}), ( Url = ${url})")
                    startActivity(mainIntent)
                    finish()
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,link,picture")
                request.parameters = parameters
                request.executeAsync()
            }
            override fun onCancel() {
            }
            override fun onError(exception: FacebookException) {
            }
        })

        loginButton.setReadPermissions(Arrays.asList(EMAIL))
        loginButton.setOnClickListener()
        {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        }


        signInButton.setOnClickListener {
            signIn()

        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }



    fun saveUser(name: String, photo: String) {

        val sharedPref = getSharedPreferences(preferences,Context.MODE_PRIVATE) ?: return
        //var account: GoogleSignInAccount? = null
        //account = GoogleSignIn.getLastSignedInAccount(this)
        with(sharedPref.edit()) {
            putString(getString(R.string.account_name), name)
            putString(getString(R.string.account_photo), photo)
            apply()
            Log.i(
                "BEB loginActivitySaveUser",
                "( name = ${name}), ( Url = ${photo})"
            )

        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

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

