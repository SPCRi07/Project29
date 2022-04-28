package com.mca.project29.loginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.mainScreens.HomeMain
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.usersimp
import com.mca.project29.databinding.ActivityLoginOtpEnterBinding

class LoginOtpEnter : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOtpEnterBinding
    private lateinit var mauth : FirebaseAuth
    private lateinit var sessionmanager: Sessionmanager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginOtpEnterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        mauth= FirebaseAuth.getInstance()
        sessionmanager= Sessionmanager(applicationContext)
        var otp=""
        val verid= intent.extras?.get("verificationId")
        binding.verifyBtn.setOnClickListener {
            binding.progressotpenter.visibility=View.VISIBLE
             otp= binding.otp1.text.toString() + binding.otp2.text + binding.otp3.text +binding.otp4.text +binding.otp5.text +binding.otp6.text
            val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                verid.toString(), otp)
            signInWithPhoneAuthCredential(credential)
        }
        binding.otp1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                    binding.otp2.requestFocus()
                   otp+=p0
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.otp2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                    binding.otp3.requestFocus()
                    otp+=p0
                }
                if (p0?.length ==0)
                {
                    binding.otp1.requestFocus()
                }


            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 0) {
                    binding.otp1.requestFocus()
                }
                }
        })

        binding.otp3.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                    binding.otp4.requestFocus()
                    otp+=p0
                }

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 0) {
                    binding.otp2.requestFocus()
                }
            }
        })

        binding.otp4.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                    binding.otp5.requestFocus()
                    otp+=p0
                }

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 0) {
                    binding.otp3.requestFocus()
                }
            }
        })

        binding.otp5.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                    binding.otp6.requestFocus()
                    otp+=p0
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 0) {
                    binding.otp4.requestFocus()
                }
            }
        })

        binding.otp6.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length ==1)
                {
                  binding.verifyBtn.requestFocus()
                    otp+=p0
                }
                else{
                    binding.otp5.requestFocus()
                }

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length == 0) {
                    binding.otp5.requestFocus()
                }
            }
        })
        }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mauth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result.user
                   // startActivity(intent)
                    val db = Firebase.firestore
                    val use= usersimp(user?.uid.toString(),user?.email.toString(),user?.displayName.toString(),user?.phoneNumber.toString())
                    db.collection("users").document(user?.uid.toString()).set(use).addOnCompleteListener {

                        val intent= Intent(applicationContext, HomeMain::class.java)

                        sessionmanager.setUid(task.result.user?.uid.toString())
                        sessionmanager.createLoginSession(task.result.user?.uid.toString(),task.result.user?.displayName.toString())
                        startActivity(intent)
                        finish()
                    }


                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Snackbar.make(binding.root,"OTP is not Valid",Snackbar.LENGTH_LONG).show()
                    }
                }

            }
    }

    }

