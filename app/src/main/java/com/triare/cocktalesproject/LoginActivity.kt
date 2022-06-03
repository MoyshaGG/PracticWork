package com.triare.cocktalesproject

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.triare.cocktalesproject.model.UserDto


class LoginActivity : AppCompatActivity() {

    companion object {
        val RC_SIGN_IN = 4422
        val saved_high_score_key = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(Application())

        val callbackManager = CallbackManager.Factory.create()


        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        //   val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        //     val account = GoogleSignIn.getLastSignedInAccount(this)
        signInButton.setOnClickListener {
            signIn()
            saveUser()
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
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
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

