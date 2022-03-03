package com.mca.project29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mca.project29.databinding.ActivityLoginwithOtpBinding
import com.mca.project29.databinding.ActivitySplashscreenBinding

private var _binding: ActivityLoginwithOtpBinding? = null

class LoginwithOtp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    var binding=ActivityLoginwithOtpBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)


        binding.loginotp.setOnClickListener {
            binding.progressotpactivity.visibility = View.VISIBLE

        }

    }
}