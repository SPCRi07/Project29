package com.mca.project29.mainScreens.ProductPage

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.dataModel.Product
import com.mca.project29.databinding.ActivityProductPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "param1"

class Productpagefragment : Fragment() {
     private var param1: String? = null
    private var _binding: ActivityProductPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            gettabs(param1.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= ActivityProductPageBinding.inflate(inflater, container, false)
        val view=binding.root
        val txt="Products in $param1"
        binding.productnametext.text=txt
        return view

     }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object {

        fun newInstance(param1: String) =
            Productpagefragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
    private fun gettabs(name:String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val db = FirebaseFirestore.getInstance()
            val productar :ArrayList<Product> = ArrayList()
            val data= db.collection("Data").document("Products").collection(name)
                .get()
                .await()

            for(item in data.documents){
                val a= item.toObject<Product>()
                if (a != null) {
                    productar.add(a)
                }
             //   Log.d(ContentValues.TAG, "product list: ${a.toString()}")
            }
            withContext(Dispatchers.Main){

                val adapter= productcardrecyclerview(requireContext(),productar)
                 binding.productrecyclerview.adapter=adapter
            }
        }
        catch (e: Exception)
        {
            Log.d(ContentValues.TAG, "gettabs Exception:$e ")
        }
    }
}