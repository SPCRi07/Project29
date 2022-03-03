package com.mca.project29

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.compose.material.Snackbar
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.mca.project29.databinding.ActivityLoginOtpEnterBinding

class LoginOtpEnter : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOtpEnterBinding
    private var mauth : FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginOtpEnterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        var otp=""
        val verid= intent.extras?.get("verificationId")
        binding.verifyBtn.setOnClickListener {
            binding.progressotpenter.visibility=View.VISIBLE
             otp= binding.otp1.text.toString() + binding.otp2.text + binding.otp3.text +binding.otp4.text +binding.otp5.text +binding.otp6.text
            com.google.android.material.snackbar.Snackbar.make(view,otp,com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()
            val credential = PhoneAuthProvider.getCredential(verid.toString(), otp)
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
        mauth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    binding.progressotpenter.visibility=View.INVISIBLE
                    com.google.android.material.snackbar.Snackbar.make(binding.root,"success",com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()

                    val user = task.result?.user

                    Log.d(TAG, "signInWithPhoneAuthCredential: $user")
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        binding.progressotpenter.visibility=View.INVISIBLE

                    }
                    // Update UI
                }
            }
    }
    }

