package com.mca.project29.onscreenBoarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.mca.project29.LoginActivity
import com.mca.project29.R
import com.mca.project29.databinding.ActivitySplashscreenBinding

class SplashScreen : Fragment() {

    private var _binding: ActivitySplashscreenBinding? = null


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ActivitySplashscreenBinding.inflate(inflater, container, false)
        val view = binding.root

        var animation = AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation)
        binding.appname.animation = animation
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_transtition)
        binding.logo.animation = animation2

        var handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if(onboardingfinished()){
                        val intent=Intent(context,LoginActivity::class.java)
                        startActivity(intent)
            }
            else
            {
                findNavController().navigate(R.id.action_splashScreen_to_viewpager3)

            }
        }, 3000)

        return view
    }
    private fun onboardingfinished() :Boolean{
        val sharePref =requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharePref.getBoolean("Finished",false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



