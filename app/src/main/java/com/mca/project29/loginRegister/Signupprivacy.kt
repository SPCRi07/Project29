package com.mca.project29.loginRegister

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.ActivitySignupprivacyBinding
import kotlin.random.Random


class Signupprivacy : AppCompatActivity() {
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivitySignupprivacyBinding
    private lateinit var sessionmanager: Sessionmanager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupprivacyBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
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
               }
                else
                    {
                        binding.confirmpassword.error = "Password are not same"
                        binding.password.error = "Password are not same"
                    }

            })
        binding.signupbacktofirstbtn.setOnClickListener {
            val intent= Intent(applicationContext,Signupbasic::class.java)
            startActivity(intent)
        }

        binding.signupbtn.setOnClickListener {

            val email=binding.emailid.editText?.text.toString()
            val mobile=binding.mobilenum.editText?.text.toString()
            val password=binding.password.editText?.text.toString()
            val confirmpass=binding.confirmpassword.editText?.text.toString()
            if (email.isEmpty() || !validateEmail(email))
            {
                    binding.emailid.error="EmailID is Invalid "
            }
            else if(mobile.isEmpty() || mobile.length !=10)
            {
                binding.mobilenum.error="Mobile Number is Invalid"
            }
            else if(password.isEmpty())
            {
                binding.password.error="Password should not be"
            }
            else if(!isValidPassword(password))
            {
                binding.password.error="Password is not valid"
            }
            else if(confirmpass.isEmpty())
            {
                binding.confirmpassword.error="Password should not be"
            }
            else if(!isValidPassword(confirmpass))
            {
                binding.confirmpassword.error="Password is not valid"
            }
            else
            {
                insertintodata(email,mobile,password)
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
        val a= Random.nextInt(1,10000)
        val id= fname!![1] + mobile.substring(1,5) +a
        Log.d(TAG, "insertintodata: $a")
        val db = Firebase.firestore

        val user=user(id, fname, lname,false,city,address,email,mobile, password)
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    data class user(
        val id:String?=null,
        val fname: String? = null,
        val lname: String?=null,
        @field:JvmField
        val isVeg: Boolean? = null,
        val city:String? =null,
        val address: String? = null,
        val email:String? =null,
        val mobile: String? = null,
        val password: String? = null,
    )



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