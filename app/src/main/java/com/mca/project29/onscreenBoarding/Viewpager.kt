package com.mca.project29.onscreenBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mca.project29.databinding.FragmentViewpagerBinding


class Viewpager : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentViewpagerBinding.inflate(inflater, container, false)
        val view=binding.root



        val fragmentList= arrayListOf<Fragment>(
            FirstNav(),
            SecNav(),
            ThirdNav()
        )
        val adapter= ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewpager.adapter=adapter

        return view
    }
}