package com.mca.project29.onscreenBoarding

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
import com.mca.project29.loginRegister.LoginOption
import com.mca.project29.R
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.ActivitySplashscreenBinding
import com.mca.project29.mainScreens.HomeMain

class SplashScreen : Fragment() {

    private var _binding: ActivitySplashscreenBinding? = null
    private lateinit var sessionmanager: Sessionmanager

   private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivitySplashscreenBinding.inflate(inflater, container, false)
        val view = binding.root
        val context =context
        sessionmanager = Sessionmanager(context)
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.blink)
        binding.logo.animation=animation2
        var animation = AnimationUtils.loadAnimation(context, R.anim.blink)
        binding.appname.animation = animation

        var handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({

            if(sessionmanager.isLoggedIn)
            {
                val intent=Intent(context, HomeMain::class.java)
                startActivity(intent)
            }
           else if((sessionmanager.isplashcomplete())){
                val intent=Intent(context, LoginOption::class.java)
                startActivity(intent)
            }
            else
            {
                findNavController().navigate(R.id.action_splashScreen_to_viewpager3)
            }
        }, 1000)

        return view
    }
//    private fun onboardingfinished() :Boolean{
//        val sharePref =requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
//        return sharePref.getBoolean("Finished",false)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



