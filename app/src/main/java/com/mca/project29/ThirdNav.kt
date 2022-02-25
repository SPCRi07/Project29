package com.mca.project29

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mca.project29.databinding.FragmentFirstNavBinding
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
            Navigation.findNavController(view).navigate(R.id.action_thirdNav_to_secNav)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}