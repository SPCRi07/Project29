package com.mca.project29.loginRegister

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mca.project29.mainScreens.HomeMain
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var sessionmanager: Sessionmanager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth = Firebase.auth
        sessionmanager=Sessionmanager(applicationContext)
        binding.loginbtn.setOnClickListener {
            binding.progressmainactivity.visibility=View.VISIBLE
                val email =binding.emailid.editText?.text.toString()
                val password =binding.password.editText?.text.toString()



            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                             binding.progressmainactivity.visibility=View.INVISIBLE
                        val user = auth.currentUser
                       // Log.d(TAG, "onCreate: user uid" +user?.uid.toString())
                        sessionmanager.setUid(user?.uid.toString())
                        val intent= Intent(applicationContext, HomeMain::class.java)
                        startActivity(intent)

                    } else {
                         binding.progressmainactivity.visibility=View.INVISIBLE
                           Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        binding.password.error="Password is Wrong"
                        binding.emailid.error="Email is Wrong"

                    }
                }
            Handler(mainLooper).postDelayed(
                {
                    if(binding.password.isErrorEnabled){
                        binding.password.isErrorEnabled=false

                        binding.emailid.isErrorEnabled=false
                    }

                }
                ,5000)
        }


    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null)
        {val intent= Intent(applicationContext, HomeMain::class.java)
        startActivity(intent)}
  //      Toast.makeText(applicationContext, "current user:- "+currentUser.email.toString(), Toast.LENGTH_SHORT).show()
    }
}
