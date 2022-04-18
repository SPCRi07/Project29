package com.mca.project29.mainScreens.Editprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.user
import com.mca.project29.databinding.ActivitySignupprivacyBinding
import com.mca.project29.loginRegister.Signupbasic
import com.mca.project29.mainScreens.HomeMain

class Profileprivacy : AppCompatActivity() {
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

        val em= intent.extras?.get("email").toString()
        val mo= intent.extras?.get("mobile").toString()
        val pa= intent.extras?.get("password").toString()
        val up="Update"
        binding.signupbtn.text=up
        binding.confirmpassword.hint="New Password"
        binding.password.hint="Old Password"
        binding.emailid.editText?.setText(em)
        binding.mobilenum.editText?.setText(mo)
        binding.signupbacktofirstbtn.setOnClickListener {
            val intent= Intent(applicationContext, Signupbasic::class.java)
            startActivity(intent)
        }


        binding.signupbtn.setOnClickListener {

            binding.progresssignupactivity.visibility= View.VISIBLE
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
            else if(confirmpass.isEmpty())
            {
                binding.confirmpassword.error="Confrim Password should not be Empty"
            }
            else if(!isValidPassword(confirmpass))
            {
                binding.confirmpassword.error="Password is not valid"
            }
            else if(pa != password)
            {
                binding.password.error="Old password is not matched"
            }
            else if(pa == confirmpass)
            {
                binding.password.error="Old password cannot be your new Password"
            }


            else
            {
                val data: HashMap<String, String?> = sessionmanager.userDetails
                val fname = data[Sessionmanager.fName]
                val lname = data[Sessionmanager.lName]
                val city = data[Sessionmanager.City].toString()
                val address = data[Sessionmanager.Address]
                val id = sessionmanager.getUid[Sessionmanager.Uid].toString()
                val user= user(id, fname, lname,false,city,address,em,mo, confirmpass)
                val db=Firebase.firestore
                val map: MutableMap<String, Any> = HashMap()
                map["id"] = id.toString()
                map["email"]=email
               map["password"]=confirmpass
                map["fname"]=fname.toString()
                map["lname"]=lname.toString()
                map["mobile"]=mobile
                map["city"]=city
                map["address"]=address.toString()
                map["isVeg"]=false

                db.collection("users").document(id).update(map).addOnCompleteListener {
                    if (it.isSuccessful) {
                        sessionmanager.setUid(id)
                        Snackbar.make(view,"Your Profile Updated Sucessfully",Snackbar.LENGTH_LONG).show()
                        val i=Intent(applicationContext,HomeMain::class.java)
                        startActivity(i)
                    }
                }
            }

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