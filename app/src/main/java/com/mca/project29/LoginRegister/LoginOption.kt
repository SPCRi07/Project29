package com.mca.project29.LoginRegister

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mca.project29.MainScreens.HomeMain
import com.mca.project29.R
import com.mca.project29.databinding.ActivityLoginOptionBinding


class LoginOption : AppCompatActivity() {
    val RC_SIGN_IN=1
    var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var binding: ActivityLoginOptionBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginOptionBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth=Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.signInButton.setSize(SignInButton.SIZE_WIDE)
        binding.signInButton.setOnClickListener {
            signIn()
        }
        binding.btncustomemail.setOnClickListener {
            val intent=Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnphone.setOnClickListener {
            val intent=Intent(applicationContext,LoginwithOtp::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        var currentUser = auth.currentUser

    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient?.signInIntent!!
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
             val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.d(TAG, "handleSignInResult: user"+account?.idToken.toString())
        Toast.makeText(applicationContext, "signed in "+account.displayName.toString(), Toast.LENGTH_SHORT).show()

            firebaseAuthWithGoogle(account.idToken.toString())
        } catch (e: ApiException) {
             Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "firebaseAuthWithGoogle: "+user?.uid.toString())
                     val intent= Intent(applicationContext, HomeMain::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                 }
            }
    }

}
