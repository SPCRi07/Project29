package com.mca.project29.mainScreens

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mca.project29.R
import com.mca.project29.dataModel.Product
import com.mca.project29.databinding.ActivityProductPageBinding
import com.mca.project29.mainScreens.homeFragment.homecardrecyclerview
import com.mca.project29.productcardrecyclerview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class productPage : AppCompatActivity() {
    lateinit  var binding :ActivityProductPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductPageBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val product=intent.extras?.get("product")
        gettabs(product.toString())

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
                Log.d(TAG, "product list: ${a.toString()}")
            }
            withContext(Dispatchers.Main){

                val adapter=productcardrecyclerview(applicationContext,productar)
                binding.productrecyclerview.adapter=adapter
            }
        }
        catch (e:Exception)
        {
            Log.d(TAG, "gettabs Exception:$e ")
        }
    }
}
