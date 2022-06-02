package com.triare.cocktalesproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
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
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val updateExit = findViewById<TextView>(R.id.kukaracha)
        updateExit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

        }
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
        val defaultValue = resources.getString(R.string.account_name)


    }

    override fun onStart() {
        super.onStart()
        var account: GoogleSignInAccount? = null
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val accountName =  sharedPref.getString(getString(R.string.account_name), account?.displayName.toString())
        val accountPhoto =  sharedPref.getString(getString(R.string.account_photo), account?.photoUrl.toString())

        if (account != null && getExit() != "exit") {

            val userInfo: String = account.displayName.toString()
            val photoUrl: String = account.photoUrl.toString()
            val userDto: UserDto
            userDto = UserDto(userInfo, photoUrl)
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("user", userDto)
            startActivity(mainIntent)


            Log.i(
                "UserInfo",
                "username =${account.displayName},email =${account.email},picture${account.photoUrl}"
            )
        }


    }

    private fun getExit(): String? {
        val arguments = intent.extras
        return arguments?.getString("exit")
    }

    private fun signIn() {

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val mainIntent = Intent(this, MainActivity::class.java)
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            val userInfo: String = account.displayName.toString()
            val photoUrl: String = account.photoUrl.toString()
            val userDto: UserDto
            userDto = UserDto(userInfo, photoUrl)
            mainIntent.putExtra("user", userDto)
            startActivity(mainIntent)
            // Signed in successfully, show authenticated UI.
            //   updateUI(account)
        } catch (e: ApiException) {

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //  updateUI(null)
        }
    }

}

