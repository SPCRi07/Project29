package com.mca.project29.onscreenBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.mca.project29.R
import com.mca.project29.databinding.FragmentFirstNavBinding

class FirstNav : Fragment() {

    private var _binding: FragmentFirstNavBinding? = null


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     _binding= FragmentFirstNavBinding.inflate(inflater, container, false)
        val view=binding.root
        val viewpager=activity?.findViewById<ViewPager2>(R.id.viewpager)


            binding.navfirst.setOnClickListener {
                viewpager?.currentItem=1
             }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
