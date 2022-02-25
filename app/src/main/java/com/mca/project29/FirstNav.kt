package com.mca.project29

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.Navigation
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

            binding.navfirst.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_firstNav_to_secNav)
            }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}