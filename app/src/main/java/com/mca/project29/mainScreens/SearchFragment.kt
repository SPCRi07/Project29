package com.mca.project29.mainScreens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.dataModel.newProduct
import com.mca.project29.databinding.FragmentSearchBinding
import com.mca.project29.mainScreens.ProductPage.productcardrecyclerview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


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
            val productar: ArrayList<newProduct> = ArrayList()

            val quer = db.collection("Products") .whereArrayContains("keywords", name.trim()).limit(50).get().await()

            if (quer.documents.size > 0) {
                  for (item in quer.documents) {
                      val a = item.toObject<newProduct>()
                      if (a != null) {
                          productar.add(a)
                      }
                  }
              }

            withContext(Dispatchers.Main) {
                binding.searchproductrecyclerview.visibility = View.VISIBLE
                val adapter = productcardrecyclerview(requireContext(), productar)
                binding.searchproductrecyclerview.adapter = adapter
            }

        }

        catch (e:Exception){
            Log.d(TAG, "search Exception: " + e.message)
        }
    }





}