package com.mca.project29.mainScreens.CartFragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class CartFragment : Fragment(),onclicklistener {

    private var _binding: FragmentCartBinding? = null
     private val binding get() = _binding!!
    private lateinit var sessionmanager: Sessionmanager
    private var userid:String?=null
    @SuppressLint("SetTextI18n")
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



    private fun getcartitems(id:String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val db = FirebaseFirestore.getInstance()
            val productar: ArrayList<cartitem> = ArrayList()

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
                }
                else
                {
                    binding.cartgif.visibility=View.VISIBLE
                    binding.errortextcart.visibility=View.VISIBLE
                }

            }
        }
        catch (e: Exception){
            Log.d(ContentValues.TAG, "search Exception: " + e.message)
        }

    }
    override fun onpriceClick(price: String?, quantity: String?) {

        val pri= price?.let { Integer.parseInt(it) }
        val q=(quantity?.let { Integer.parseInt(it) })
        val total= pri?.times(q!!)
        val firsttotal=binding.totaltxt.text.toString()
        binding.totaltxt.text=total.toString()

    }

}

