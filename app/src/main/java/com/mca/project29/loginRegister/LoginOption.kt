package com.mca.project29.loginRegister

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.mainScreens.HomeMain
import com.mca.project29.R
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.usersimp
import com.mca.project29.databinding.ActivityLoginOptionBinding


class LoginOption : AppCompatActivity() {

    val RC_SIGN_IN=1
    var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var sessionmanager: Sessionmanager
    private lateinit var binding: ActivityLoginOptionBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginOptionBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth=Firebase.auth
        sessionmanager= Sessionmanager(applicationContext)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.signInButton.setSize(SignInButton.SIZE_WIDE)
        binding.signInButton.setOnClickListener {
            signIn()
        }

        binding.btnsignup.setOnClickListener {
            val intent=Intent(applicationContext,Signupbasic::class.java)
            startActivity(intent)
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
        val currentUser = auth.currentUser
        if(currentUser!=null){
            Snackbar.make(binding.root,"Logged in "+currentUser.displayName.toString(),Snackbar.LENGTH_LONG).show()
            val intent= Intent(applicationContext, HomeMain::class.java)
            startActivity(intent)
        }
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
                    val db = Firebase.firestore
                    val use=usersimp(user?.uid.toString(),user?.email.toString(),user?.displayName.toString(),user?.phoneNumber.toString())
                    db.collection("users").document(user?.uid.toString()).set(use).addOnCompleteListener {

                        sessionmanager.setUid(user?.uid.toString())
                        sessionmanager.createLoginSession(user?.uid.toString(),user?.displayName.toString())
                        //     Log.d(TAG, "firebaseAuthWithGoogle: "+user?.uid.toString())
                        val intent= Intent(applicationContext, HomeMain::class.java)
                        startActivity(intent)
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                 }

            }
    }


}
