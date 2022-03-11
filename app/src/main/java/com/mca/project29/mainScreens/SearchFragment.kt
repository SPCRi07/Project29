package com.mca.project29.mainScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mca.project29.R
import com.mca.project29.databinding.FragmentHomeBinding
import com.mca.project29.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSearchBinding.inflate(inflater, container, false)
        val view=binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}