package com.mca.project29.mainScreens.homeFragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.storage
import com.mca.project29.R
import com.mca.project29.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import io.grpc.Context

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val view=binding.root
        val db = Firebase.firestore
        val Stringar= ArrayList<String>()

     //   val pathReference = storageRef.child("images/stars.jpg")

        val storageReference = Firebase.storage.reference
        val abc=FirebaseStorage.getInstance().reference.child("screenimages/masala.jpg")
        Picasso.get().load("gs://myroshopping.appspot.com/screenimages/biscuits.jpg").into(binding.img)
       Log.d(TAG, "storageReference "+storageReference.path)
        val Names=arrayOf("Banana","Grape","Guava","Mango")
        val ImageIds=arrayOf(R.drawable.ic_cancel,R.drawable.addtocart,R.drawable.arrow_forward_24,R.drawable.ic_check)
        db.collection("discountimages")
            .get()
            .addOnSuccessListener { result ->
                   for (document in result) {
                    Log.d(TAG, "photos${document.id} => ${ document["imagepath"]}")
                   Stringar.add(document.data.toString())

                 }

            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        val imageadapter =myPagerAdapterHome(requireContext(),Stringar)

        binding.screenlist.adapter=imageadapter
       val adapter=homecardrecyclerview(requireContext(),Names,ImageIds)
        binding.homecardlist.adapter=adapter
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
