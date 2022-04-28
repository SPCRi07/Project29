package com.mca.project29.mainScreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mca.project29.R
import com.mca.project29.databinding.ActivityAboutmeBinding

class Aboutme : AppCompatActivity() {
   private lateinit var binding : ActivityAboutmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAboutmeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.backaboutme.setOnClickListener {

            val i=Intent(applicationContext,HomeMain::class.java)
            startActivity(i)
        }

    }

}
