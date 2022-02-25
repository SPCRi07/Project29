package com.mca.project29

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mca.project29.databinding.FragmentFirstNavBinding
import com.mca.project29.databinding.FragmentSecNavBinding

class SecNav : Fragment() {
    private var _binding: FragmentSecNavBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSecNavBinding.inflate(inflater, container, false)
        val view=binding.root

        binding.navsecond.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_secNav_to_thirdNav)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}