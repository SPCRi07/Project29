package com.mca.project29.mainScreens.CartFragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.cartitem
import com.mca.project29.dataModel.onclicklistener
import com.mca.project29.databinding.FragmentCartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Integer.parseInt
import java.util.*
import kotlin.collections.ArrayList

class CartFragment : Fragment(),onclicklistener {

    private var _binding: FragmentCartBinding? = null
     private val binding get() = _binding!!
    private lateinit var sessionmanager: Sessionmanager
    private var userid:String?=null
    val productar: ArrayList<cartitem> = ArrayList()


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentCartBinding.inflate(inflater, container, false)
        val view=binding.root
        sessionmanager= Sessionmanager(context)
        binding.carttext.text=sessionmanager.getname[Sessionmanager.Name] + "' Cart"
        userid= sessionmanager.getUid[Sessionmanager.Uid].toString()
        getcartitems(userid.toString())
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }




    @RequiresApi(Build.VERSION_CODES.N)

    private fun getcartitems(id:String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val db = FirebaseFirestore.getInstance()
            val quer = db.collection("cart") .whereEqualTo("uid", id.trim()).limit(50).get().await()
            if (quer.documents.size > 0) {
                for (item in quer.documents) {

                    val a = item.toObject<cartitem>()
                    if (a != null) {
                        productar.add(a)
                    }
                }
            }
            



            withContext(Dispatchers.Main) {

                if (quer.documents.size > 0){

                    binding.cartgif.visibility=View.GONE
                    binding.errortextcart.visibility=View.GONE
                    binding.cartrecyclerview.visibility = View.VISIBLE
                    binding.cartotal.visibility=View.VISIBLE
                    val adapter = userid?.let { cartrecyclerview(requireContext(), productar, it,this@CartFragment) }
                    binding.cartrecyclerview.adapter = adapter
                    setprice(productar)
                  }

                else
                {
                    binding.cartgif.visibility=View.VISIBLE
                    binding.errortextcart.visibility=View.VISIBLE
                }
            }

        }

        catch (e: Exception){
            Log.d(TAG, "Cart Exception: " + e.message)
        }

    }

    private fun setprice(productar: ArrayList<cartitem>) {

        val s: ArrayList<Int> = ArrayList()

        for (i in productar)
        {
           s.add(parseInt(i.price.toString()))
        }
        binding.totaltxt.text =s.sum().toString()
    }


    override fun onpriceClick(price: String?, quantity: String?, type: String?) {
        
        val pri= price?.let { Integer.parseInt(it) }
        val q=(quantity?.let { Integer.parseInt(it) })
        val before= parseInt(binding.totaltxt.text.toString())
        if (type.equals("minus"))
        {
            if (q!! >=1)
            {
                val total = before.minus(pri!!)
                binding.totaltxt.text = total.toString()
            }
        }
        if (type.equals("plus"))
        {
            if (q!! >=1)
            {
                val total = before.plus(pri!!)
                binding.totaltxt.text = total.toString()
            }
        }
        if (type.equals("remove"))
        {
                val total = before.minus(pri!!)
                binding.totaltxt.text = total.toString()
        }



//            if (q!! >0) {
//                val total = before.plus(pri?.times(q)?.minus(pri)!!)
//                binding.totaltxt.text = total.toString()
//            }


        }
}


