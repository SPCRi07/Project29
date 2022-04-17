package com.mca.project29.mainScreens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.Sessionmanager
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
    private lateinit var Sessionmanager:Sessionmanager

    var db: FirebaseFirestore? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentSearchBinding.inflate(inflater, container, false)
        val view=binding.root
        Sessionmanager= Sessionmanager(context)
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

                if (quer.documents.size > 0 ) {
                    binding.errortextsearch.visibility=View.GONE
                    binding.searchgif.visibility=View.GONE
                    binding.searchproductrecyclerview.visibility = View.VISIBLE
                    val userid = Sessionmanager.getUid.toString()
                    val adapter = productcardrecyclerview(requireContext(), productar, userid)
                    binding.searchproductrecyclerview.adapter = adapter
                }

                else
                {
                    val txt="Sorry We don't have $name"
                    binding.errortextsearch.text =txt
                    binding.errortextsearch.visibility=View.VISIBLE
                    binding.searchgif.visibility=View.VISIBLE
                }
                }
        }

        catch (e:Exception){
            Log.d(TAG, "search Exception: " + e.message)
        }
    }





}