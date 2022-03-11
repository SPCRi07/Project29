package com.mca.project29.loginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.mca.project29.databinding.ActivityLoginwithOtpBinding
import java.util.concurrent.TimeUnit

class LoginwithOtp : AppCompatActivity() {

    private lateinit var binding: ActivityLoginwithOtpBinding
    private var mauth :FirebaseAuth?=null
    private var mcallback :PhoneAuthProvider.OnVerificationStateChangedCallbacks?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginwithOtpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mauth= FirebaseAuth.getInstance()

        binding.loginotp.setOnClickListener {
            binding.progressotpactivity.visibility = View.VISIBLE
            val ph=binding.phonenumtext.editText?.text.toString()
            if(ph.length != 10){
                binding.phonenumtext.error="Enter Valid Phone Number"
            }
            else
            {
                otsend("+91$ph")
            }
        }
    }
        fun otsend(ph :String){

            val options = mauth?.let {
                PhoneAuthOptions.newBuilder(it)
                    .setPhoneNumber(ph)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        override fun onCodeSent(
                            verificationId: String,
                            forceResendingToken: PhoneAuthProvider.ForceResendingToken

                        ) {
                            binding.progressotpactivity.visibility=View.INVISIBLE
                            com.google.android.material.snackbar.Snackbar.make(binding.root,"Code Sent",1000).show()
                            val intent = Intent(applicationContext, LoginOtpEnter::class.java)
                            intent.putExtra("verificationId",verificationId)
                            startActivity(intent)
                            finish()
                        }

                        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {


                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                        }
                    })
                    .build()
            }

            if (options != null) {
                PhoneAuthProvider.verifyPhoneNumber(options)
            }
        }
        }
