package com.mca.project29.mainScreens.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mca.project29.R
import com.mca.project29.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root
        val Names=arrayOf("Banana","Grape","Guava","Mango")
        val ImageIds=arrayOf(R.drawable.ic_cancel,R.drawable.addtocart,R.drawable.arrow_forward_24,R.drawable.ic_check)
//        val layout= LinearLayoutManager(context)
//        binding.homecardlist.layoutManager=layout
       val adapter=homecardrecyclerview(requireContext(),Names,ImageIds)
        binding.homecardlist.adapter=adapter
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
