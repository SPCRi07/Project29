package com.mca.project29.mainScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.FragmentSettingBinding


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
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}