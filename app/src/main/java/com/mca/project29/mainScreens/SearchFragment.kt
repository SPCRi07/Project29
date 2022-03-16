package com.mca.project29.mainScreens

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.R
import com.mca.project29.dataModel.Product
import com.mca.project29.databinding.FragmentHomeBinding
import com.mca.project29.databinding.FragmentSearchBinding
import com.mca.project29.mainScreens.ProductPage.productcardrecyclerview
import com.mca.project29.mainScreens.homeFragment.homecardrecyclerview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    var db: FirebaseFirestore? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSearchBinding.inflate(inflater, container, false)
        val view=binding.root
         binding.searchquerybtn.setOnClickListener {
           db = FirebaseFirestore.getInstance()
             val query=binding.searchinputlayout.editText?.text?.trim().toString()
             search(query)
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    private fun search(name:String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val db = FirebaseFirestore.getInstance()
            val productar :ArrayList<Product> = ArrayList()

          val k= db.collection("Data").whereArrayContains("keywords", name.trim()).get().await()


            for( i in k) {

            //    val a = k.documents.toob<Product>()
             //   productar.add(a)
              Log.d(TAG, "product list:"+  i.get(name) )
            }
                binding.searchproductrecyclerview.visibility = View.VISIBLE
                val adapter = productcardrecyclerview(requireContext(), productar)
                binding.searchproductrecyclerview.adapter = adapter

        }

        catch (e: Exception)
        {
            Log.d(TAG, "search Exception:$e ")
        }
    }

}