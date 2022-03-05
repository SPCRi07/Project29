package com.mca.project29.onscreenBoarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mca.project29.LoginRegister.LoginActivity
import com.mca.project29.databinding.FragmentThirdNavBinding

class ThirdNav : Fragment() {
    private var _binding: FragmentThirdNavBinding? = null


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentThirdNavBinding.inflate(inflater, container, false)
        val view=binding.root

        binding.navthird.setOnClickListener {
            val intent= Intent(context, LoginActivity::class.java)
            onBoardingFinished()
            startActivity(intent)
        }


        return view
    }

    private fun onBoardingFinished(){
        val sharePref =requireActivity().getSharedPreferences("onboarding",Context.MODE_PRIVATE)
        val editor=sharePref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}