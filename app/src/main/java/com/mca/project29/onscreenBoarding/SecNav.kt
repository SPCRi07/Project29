package com.mca.project29.onscreenBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.mca.project29.R
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
            val viewpager=activity?.findViewById<ViewPager2>(R.id.viewpager)


            binding.navsecond.setOnClickListener {
                viewpager?.currentItem=2
            }

        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}