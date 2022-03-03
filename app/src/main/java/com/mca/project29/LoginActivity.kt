package com.mca.project29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.mca.project29.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.loginbtn.setOnClickListener {

            binding.progressmainactivity.visibility=View.VISIBLE
            Handler(mainLooper).postDelayed(
                {
                    binding.progressmainactivity.visibility=View.INVISIBLE
                }
                ,1000)
        }
    }
}
