package com.mca.project29.loginRegister

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.user
import com.mca.project29.databinding.ActivitySignupprivacyBinding
import com.mca.project29.mainScreens.HomeMain
import kotlin.random.Random


class Signupprivacy : AppCompatActivity() {
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivitySignupprivacyBinding
    private lateinit var sessionmanager: Sessionmanager
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupprivacyBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        auth = Firebase.auth
        sessionmanager=Sessionmanager(applicationContext)



        binding.confirmpassword.editText?.addTextChangedListener(
            onTextChanged = { text, start, count, after ->

                val s = binding.password.editText?.text?.trim().toString()
                Log.d(TAG, "onCreate: $s")
                Log.d(TAG, "onCreate 1: $text")
                if (text?.trim().toString() == s)
                {
                    binding.confirmpassword.isErrorEnabled=false
                    binding.password.isErrorEnabled=false
                    binding.signupbtn.isEnabled=true
               }
                else
                    {
                        binding.confirmpassword.error = "Password are not same"
                        binding.password.error = "Password are not same"
                        binding.signupbtn.isEnabled=false
                    }
            })
        binding.signupbacktofirstbtn.setOnClickListener {
            val intent= Intent(applicationContext,Signupbasic::class.java)
            startActivity(intent)
        }

        binding.signupbtn.setOnClickListener {

            binding.progresssignupactivity.visibility=View.VISIBLE
            val email=binding.emailid.editText?.text.toString()
            val mobile=binding.mobilenum.editText?.text.toString()
            val password=binding.password.editText?.text.toString()
            val confirmpass=binding.confirmpassword.editText?.text.toString()
            if (email.isEmpty())
            {
                    binding.emailid.error="EmailID is should not be empty "
            }
            else if(!validateEmail(email))
            {
                binding.emailid.error="EmailID is Invalid"
            }

            else if(mobile.isEmpty() || mobile.length !=10)
            {
                binding.mobilenum.error="Mobile Number is Invalid"
            }
            else if(password.isEmpty())
            {
                binding.password.error="Password should not be Empty"
            }
            else if(!isValidPassword(password))
            {
                binding.password.error="Password is not valid"
            }
            else if(confirmpass.isEmpty())
            {
                binding.confirmpassword.error="Confrim Password should not be Empty"
            }
            else if(!isValidPassword(confirmpass))
            {
                binding.confirmpassword.error="Password is not valid"
            }
            else
            {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                             val user = auth.currentUser
                            // Log.d(TAG, "onCreate: user uid" +user?.uid.toString())
                            sessionmanager.setUid(user?.uid.toString())
                            insertintodata(email,mobile,password)
                        } else {
                            binding.progresssignupactivity.visibility= View.INVISIBLE
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            binding.emailid.error="Email is not validated by server"

                        }
                    }


            }

        }
    }

    private fun insertintodata(email: String, mobile: String, password: String) {
        val data: HashMap<String, String?> = sessionmanager.userDetails
        val fname = data.get(Sessionmanager.fName)
        val lname = data.get(Sessionmanager.lName)
        val city = data.get(Sessionmanager.City).toString()
        val address = data.get(Sessionmanager.Address)
        val veg = data.get(Sessionmanager.Veg)
        val id= sessionmanager.getUid.toString()
        val db = Firebase.firestore

        val user= user(id, fname, lname,false,city,address,email,mobile, password)

        db.collection("users").document(id).set(user).addOnCompleteListener {
            binding.progresssignupactivity.visibility= View.INVISIBLE
            sessionmanager.createLoginSession(id,fname)
            val intent=Intent(applicationContext,HomeMain::class.java)
            startActivity(intent)
        }

    }



    fun validateEmail(email:String) :Boolean {
        return email.matches(emailPattern.toRegex())
    }
    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }


}
