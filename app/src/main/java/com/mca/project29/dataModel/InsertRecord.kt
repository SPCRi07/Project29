package com.mca.project29.dataModel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.dataModel.Product
import com.mca.project29.mainScreens.HomeMain
import com.mca.project29.mainScreens.ProductPage.productcardrecyclerview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import kotlin.random.Random

public class InsertRecord {

   // val db = Firebase.firestore

//    val p2 = Product("Badam(Almonds)", "299", "43.8", true, "3","4.5","" )
//    val p3 = Product("Ona Drakshi", "200", "46.0", true, "13","4.0","" )
//    val p4 = Product("Akhrot", "299", "29.10", true, "15","4.3","" )
//    val p5 = Product("Anjeer", "259", "26.3", true, "17","4.0","" )
//    val p6 = Product("Cashew", "249", "24.5", true, "3","4.2","" )
//    val p7 = Product("Khajua", "399", "47.63", true, "5","4.4","" )
//


//
//    db.collection("Data").document("Products").collection("Dryfruits").document(p2.name.toString()).set(p2)
//    db.collection("Data").document("Products").collection("Dryfruits").document(p3.name.toString()).set(p3)
//    db.collection("Data").document("Products").collection("Dryfruits").document(p4.name.toString()).set(p4)
//    db.collection("Data").document("Products").collection("Dryfruits").document(p5.name.toString()).set(p5)
//    db.collection("Data").document("Products").collection("Dryfruits").document(p6.name.toString()).set(p6)
//    db.collection("Data").document("Products").collection("Dryfruits").document(p7.name.toString()).set(p7)
//
//    private fun search(name:String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            db = FirebaseFirestore.getInstance()
//
//            val productar :ArrayList<Product> = ArrayList()
//            val n="snacks"
//
//            val doc= db!!.collection("Data").document("Products").collection(n).get().await()
//
//            for (i in doc)
//            {
//                update(i.getField<String>("name")!!,n)
//            }
//
//
//            withContext(Dispatchers.Main){
//
//                val adapter= productcardrecyclerview(requireContext(),productar)
//                //    binding.searchproductrecyclerview.adapter=adapter
//            }
//        }
//        catch (e: Exception)
//        {
//            Log.d(ContentValues.TAG, "search Exception:$e ")
//        }
//    }
//    fun generateKeywords(name: String): List<String> {
//        val keywords = mutableListOf<String>()
//        for (i in 0 until name.length) {
//            for (j in (i+1)..name.length) {
//                keywords.add(name.slice(i until j))
//            }
//        }
//        return keywords
//    }
//
//    suspend fun update(name:String,sname:String){
//        val data=generateKeywords(name)
//        val doc=db!!.collection("Data").document("Products").collection(sname).document(name).update("keywords",data).await()
//
//
//    }

//    private fun getresult(name:String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val db = FirebaseFirestore.getInstance()
//            val productar :ArrayList<Product> = ArrayList()
//            val k=db.collection("Data").document("Products").get().await()
//
//
//            for(item in k){
//
//                val a= item.toObject<Product>()
//                Log.d(TAG, "getresult in data: "+ item.toString())
//
//                if (a != null) {
//                 //   productar.add(a)
//                      Log.d(TAG, "search list: ${a}")
//
//                }
//                //   Log.d(ContentValues.TAG, "product list: ${a.toString()}")
//            }
//
//            withContext(Dispatchers.Main){
//                val adapter= productcardrecyclerview(requireContext(),productar)
//          //      binding.searchproductrecyclerview.adapter=adapter
//            }
//
//        }
//        catch (e: Exception)
//        {
//            Log.d(TAG, "search Exception:$e ")
//        }
//    }


//    private fun getdata() = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val Names = mutableListOf<String>()
//            val ImageIds = mutableListOf<String>()
//            val db = FirebaseFirestore.getInstance()
//            val data= db.collection("Data")
//                .document("Products")
//                .collection("snacks")
//                .get()
//                .await()
//            val list = mutableListOf<newProduct>()
//            var stars: MutableMap<String, Boolean> = HashMap()
//
//            for(item in data.documents){
//                val a= item.toObject<newProduct>()
//                a?.type="snacks"
//                if (a != null) {
//                    list.add(a)
//
//                        db.collection("Products").add(a).addOnSuccessListener {
//                            Log.d(TAG, "data result: + it.result.toString()")
//                            Log.d(TAG, "data exception:  + it.exception.toString()")
//
//
//                    }
//                };
//
//               database = Firebase.database.reference
//                database.child("Products").setValue(a).addOnCompleteListener {
//                    Log.d(TAG, "data result: + it.result.toString()")
//                    Log.d(TAG, "data exception:  + it.exception.toString()")
//
//
//                }
//
//
//            }
//            withContext(Dispatchers.Main){
//            }
//        }
//
//        catch (e:Exception){
//            Log.d(TAG, "gettabserror: "+e.message)
//        }
//    }


//   // private fun search(name:String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val db = FirebaseFirestore.getInstance()
//            val productar :ArrayList<Product> = ArrayList()
//         val bev= db.collection("Data").document("Products").collection("Beverages").whereArrayContains("keywords",name.trim()).get().await()
//            val cookies= db.collection("Data").document("Products").collection("Cookies").whereArrayContains("keywords",name.trim()).get().await()
//            val Dryfruits= db.collection("Data").document("Products").collection("Dryfruits").whereArrayContains("keywords",name.trim()).get().await()
//            val Flour= db.collection("Data").document("Products").collection("Flour").whereArrayContains("keywords",name.trim()).get().await()
//            val Fruits= db.collection("Data").document("Products").collection("Fruits").whereArrayContains("keywords",name.trim()).get().await()
//            val Masala= db.collection("Data").document("Products").collection("Masala").whereArrayContains("keywords",name.trim()).get().await()
//            val Vegetables= db.collection("Data").document("Products").collection("Vegetables").whereArrayContains("keywords",name.trim()).get().await()
//            val snacks= db.collection("Data").document("Products").collection("snacks").whereArrayContains("keywords",name.trim()).get().await()

//            if (bev.documents.size > 0) {
//                  for (item in bev.documents) {
//                      val a = item.toObject<Product>()
//                      if (a != null) {
//                          productar.add(a)
//                      }
//                  }
//              }
//            if (Dryfruits.documents.size > 0) {
//                for (item in Dryfruits.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (Flour.documents.size > 0) {
//                for (item in Flour.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (cookies.documents.size > 0) {
//                for (item in cookies.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (Fruits.documents.size > 0) {
//                for (item in Fruits.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (Masala.documents.size > 0) {
//                for (item in Masala.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (Vegetables.documents.size > 0) {
//                for (item in Vegetables.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//            if (snacks.documents.size > 0) {
//                for (item in snacks.documents) {
//                    val a = item.toObject<Product>()
//                    if (a != null) {
//                        productar.add(a)
//                    }
//                }
//            }
//
//
//            Log.d(TAG, "product fetch from all : "+ productar.size)
//
//            binding.searchproductrecyclerview.visibility = View.VISIBLE
//                val adapter = productcardrecyclerview(requireContext(), productar)
//                binding.searchproductrecyclerview.adapter = adapter
//
//        }
//
//        catch (e: Exception)
//        {
//            Log.d(TAG, "search Exception:$e ")
//        }
}

    @RequiresApi(Build.VERSION_CODES.O)
    public fun insertintocart(name: String, image: String, price: String, uid:String):Int {
       val db = Firebase.firestore
        var s:Int=5
        val r=Random.nextInt(50000,1000000).toString()
        val c= Random.nextInt(97,123).toChar().toString()
        val k= Random.nextInt(999,99999999).toString()
        val id=r.plus(c).plus(k)
        val date= LocalDateTime.now().toString()
        val item=cartitem(id,name,image,price,date,uid)
        db.collection("cart").document(id).set(item).addOnSuccessListener {
            s=1
        }
            .addOnFailureListener {
                Log.d(TAG, "insertintocart: " +it.message.toString())
                s=0
            }
        return s
    }

public fun removefromcart(id: String):Boolean {
    val r=false
    val db = FirebaseFirestore.getInstance()

    val quer = db.collection("cart").document(id).delete().addOnCompleteListener {

    }
        .addOnFailureListener {
            Log.d(TAG, "removefromcart: ${it.message.toBoolean()}")
        }


    return r
}


