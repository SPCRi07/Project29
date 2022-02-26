package com.mca.project29.onscreenBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mca.project29.databinding.ActivityMainBinding


lateinit var binding: ActivityMainBinding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
    }

}