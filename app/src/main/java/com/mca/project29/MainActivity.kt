package com.mca.project29

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.mca.project29.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var animation=AnimationUtils.loadAnimation(applicationContext, R.anim.fade_scale_animation)
        binding.demotext.animation=animation
        var animation2=AnimationUtils.loadAnimation(applicationContext, R.anim.fade_transtition)
        binding.logo.animation=animation2
        val i =Intent(applicationContext,NavigationActivity::class.java)
        var handler =Handler(mainLooper)
        handler.postDelayed({
                     startActivity(i)       
        },2000)
    }
}