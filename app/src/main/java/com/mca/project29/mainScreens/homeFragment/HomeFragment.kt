package com.mca.project29.mainScreens.homeFragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.Product
import com.mca.project29.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private lateinit var Sessionmanager:Sessionmanager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root
        Sessionmanager= Sessionmanager(context)
        getfiles()
        gettabs()
        binding.welcomemsg.text= Sessionmanager.getname[com.mca.project29.Sessionmanager.Name]
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun gettabs() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val Names = mutableListOf<String>()
            val ImageIds = mutableListOf<String>()
            val db = FirebaseFirestore.getInstance()
            val fg=parentFragmentManager
           val data= db.collection("homescreentabs")
                .get()
                .await()
                    for(item in data.documents){
                        val i= item.getString("name").toString()
                        val i2= item.getString("image")
                        Names.add(i)
                        ImageIds.add(i2.toString())
                    }

            withContext(Dispatchers.Main){
                   val adapter=homecardrecyclerview(requireContext(),Names,ImageIds,fg)
                    binding.homecardlist.adapter=adapter
            }
        }

        catch (e:Exception){
            Log.d(TAG, "get tabs error: "+e.message)
        }
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
                    binding.dotsIndicatorHome.setViewPager(binding.screenlist)
            }
        }

        catch (e:Exception)
        {
            withContext(Dispatchers.Main) {
                Log.d(TAG, "homefragement getfiles  Exception: " + e.message)
            }
        }
    }




}
