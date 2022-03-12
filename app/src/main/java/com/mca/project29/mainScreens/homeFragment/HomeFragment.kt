package com.mca.project29.mainScreens.homeFragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mca.project29.R
import com.mca.project29.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root
        getfiles()
        val Names=arrayOf("Banana","Grape","Guava","Mango")
        val ImageIds=arrayOf(R.drawable.ic_cancel,R.drawable.addtocart,R.drawable.arrow_forward_24,R.drawable.ic_check)


       val adapter=homecardrecyclerview(requireContext(),Names,ImageIds)
        binding.homecardlist.adapter=adapter


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun getfiles() = CoroutineScope(Dispatchers.IO).launch {

        try {
            val storageReference = Firebase.storage.reference

            val images=storageReference.child("screenimages/").listAll().await()
            val imageurls= mutableListOf<String>()
            for(image in images.items){
                val url=image.downloadUrl.await()
                imageurls.add(url.toString())
            }
            withContext(Dispatchers.Main){
                val imageadapter =myPagerAdapterHome(requireContext(),imageurls)
                binding.screenlist.adapter=imageadapter
            }

        }

        catch (e:Exception)
        {
            withContext(Dispatchers.Main) {
                Log.d(TAG, "getfiles: " + e.message)
            }
        }
    }

}
