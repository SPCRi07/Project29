package com.mca.project29.mainScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mca.project29.R
import com.mca.project29.databinding.ActivityHomeMainBinding

class HomeMain : AppCompatActivity() {
    private lateinit var binding: ActivityHomeMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeMainBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)

        val navController= findNavController(R.id.mainfragment)
        binding.bottomnav.setupWithNavController(navController)

    }
}