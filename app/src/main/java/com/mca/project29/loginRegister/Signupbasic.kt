package com.mca.project29.loginRegister

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mca.project29.R
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.ActivitySignupBasicBinding


class Signupbasic : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBasicBinding
    lateinit var  ar_gujarat: ArrayList<Any>
    lateinit var session:Sessionmanager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivitySignupBasicBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
      session= Sessionmanager(applicationContext)
         adddata()


        binding.signupnext.setOnClickListener {

          val fname=binding.firstname.editText?.text?.trim().toString()
          val lname=binding.lastname.editText?.text?.trim().toString()
          val address=binding.address.editText?.text?.trim().toString()
          val city=binding.city.text?.trim().toString()
          val veg=binding.vegetarianswitch.isChecked
            if(fname.isEmpty())
            {
              binding.firstname.error="Enter your First Name"
            }
          else if(lname.isEmpty())
            {
            binding.lastname.error="Enter your Last Name"
          }
          else if(address.isEmpty())
            {
            binding.address.error="Enter your address"
            }
          else if(binding.city.isPerformingCompletion)
            {
            Snackbar.make(view,"Select City",Snackbar.LENGTH_LONG).show()
          }
          else
            {


              session.signup_credentials(fname, lname, address, city, veg)
              val i=Intent(applicationContext,Signupprivacy::class.java)
              startActivity(i)
            }

        }

      binding.signupbackbtn.setOnClickListener {
        val intent=Intent(applicationContext,LoginOption::class.java)
        startActivity(intent)
      }

    }




  private fun adddata() {
    ar_gujarat = ArrayList()
    ar_gujarat.add("Ahmedabad")
    ar_gujarat.add("Anand")
    ar_gujarat.add("Bharuch")
    ar_gujarat.add("Bhavnagar")
    ar_gujarat.add("Dahod")
    ar_gujarat.add("Dwarka")
    ar_gujarat.add("Gandhinagar")
    ar_gujarat.add("Godhra")
    ar_gujarat.add("Himmatnagar")
    ar_gujarat.add("Jamnagar")
    ar_gujarat.add("Junagath")
    ar_gujarat.add("Kalol")
    ar_gujarat.add("Khambhat")
    ar_gujarat.add("Limdi")
    ar_gujarat.add("Lunawada")
    ar_gujarat.add("Mahesana")
    ar_gujarat.add("Modasa")
    ar_gujarat.add("Morbi")
    ar_gujarat.add("Nadiad")
    ar_gujarat.add("Navsari")
    ar_gujarat.add("Ode")
    ar_gujarat.add("Okha")
    ar_gujarat.add("Palanpur")
    ar_gujarat.add("Patan")
    ar_gujarat.add("Porbandar")
    ar_gujarat.add("Rajkot")
    ar_gujarat.add("Rajpipla")
    ar_gujarat.add("Surat")
    ar_gujarat.add("Surendranagar")
    ar_gujarat.add("Valsad")
    ar_gujarat.add("Vapi")
    ar_gujarat.add("Veraval")
    ar_gujarat.add("Unjha")
    ar_gujarat.add("Una")
    ar_gujarat.add("Umreth")
    ar_gujarat.add("Nadiad")
    ar_gujarat.add("V.V.Nagar")
    ar_gujarat.add("Vadodara")
    ar_gujarat.add("Others")

    val ad=ArrayAdapter(applicationContext,R.layout.dropdown_city,ar_gujarat)
    binding.city.setAdapter(ad)
  }



}

