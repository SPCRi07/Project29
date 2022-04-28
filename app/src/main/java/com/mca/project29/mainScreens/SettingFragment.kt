package com.mca.project29.mainScreens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mca.project29.R
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.FragmentSettingBinding
import com.mca.project29.mainScreens.Editprofile.editprofilebasic
import com.mca.project29.mainScreens.ProductPage.Productpagefragment
import com.mca.project29.mainScreens.ProductPage.Productpagefragment.Companion.newInstance


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
     lateinit var sessionmanager:Sessionmanager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSettingBinding.inflate(inflater, container, false)
        val view=binding.root
        sessionmanager= Sessionmanager(context)
        binding.Logoutbtn.setOnClickListener {
            Firebase.auth.signOut()
            sessionmanager.logoutUser()
        }
        binding.About.setOnClickListener {
            val i= Intent(context,Aboutme::class.java)
            startActivity(i)
        }
        binding.Feedback.setOnClickListener {
            val i= Intent(context,FeedbackActivity::class.java)
            startActivity(i)
        }


        binding.Editprofile.setOnClickListener {
            val i= Intent(context,editprofilebasic::class.java)
            startActivity(i)
        }



        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}