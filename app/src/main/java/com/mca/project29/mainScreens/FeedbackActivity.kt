package com.mca.project29.mainScreens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.mca.project29.Sessionmanager
import com.mca.project29.databinding.FragmentFeedbackFragementBinding
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: FragmentFeedbackFragementBinding
    private lateinit var Sessionmanager: Sessionmanager
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentFeedbackFragementBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        Sessionmanager= Sessionmanager(applicationContext)

        binding.submit.setOnClickListener {
            val data: MutableMap<String, Any> = HashMap()
            data["id"] = "Tokyo"
            data["feedback"] = binding.ratingbar.rating
            data["feedbacktext"]=binding.feedback.editText?.text.toString()
            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
            val currentDateAndTime: String = simpleDateFormat.format(Date())
            data["date"]=currentDateAndTime

            val db = FirebaseFirestore.getInstance()
            val quer = db.collection("feedback").add(data).addOnCompleteListener {
                if (it.isSuccessful)
                {
                 //   Log.d(TAG, "onCreate: $it")
                }
            }

        }
    }
}