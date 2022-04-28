package com.mca.project29.mainScreens.CartFragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.firestore.FirebaseFirestore
import com.mca.project29.R
import com.mca.project29.dataModel.*
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class cartrecyclerview(context: Context, arrayList: ArrayList<cartitem>, userid: String,listener:onclicklistener):
    RecyclerView.Adapter<cartrecyclerview.ViewHolder>() {
    val con = context
    val productlist = arrayList
    val uid = userid
    val listen = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.cartcarditem, parent, false)
        return ViewHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = productlist[position].name
        Picasso.get().load(productlist[position].image).into(holder.image)
        holder.price.text = "Rs.${productlist[position].price}"
        Log.d(TAG, "onBindViewHolder: $position")
        holder.plus.setOnClickListener {
            val ov = holder.quantity.text.toString()
            val s = Integer.parseInt(ov)
            val s2 = s.plus(1)
            holder.quantity.text = s2.toString()
            listen.onpriceClick(
                productlist[position].price.toString(),
                holder.quantity.text.toString(),
                "plus"
            )
        }

        holder.minus.setOnClickListener {
            val ov = holder.quantity.text
            val s = Integer.parseInt(ov.toString())
            if (s == 1) {
                Snackbar.make(
                    it,
                    "you can remove item by clicking remove button",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val s2 = s.minus(1)
                holder.quantity.text = s2.toString()
                listen.onpriceClick(
                    productlist[position].price.toString(),
                    holder.quantity.text.toString(),
                    "minus"
                )

            }
        }
        holder.remove.setOnClickListener {
            removefromcart(productlist[position].id.toString())
            listen.onpriceClick(
                productlist[position].price.toString(),
                holder.quantity.text.toString(),
                "remove"
            )
            Snackbar.make(it, "Item Removed", Snackbar.LENGTH_LONG).show()
            productlist.removeAt(position)
            notifyItemRemoved(itemCount)
        }
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<MaterialTextView>(R.id.nameincarttext)
        val price = view.findViewById<MaterialTextView>(R.id.cancartpricenow)
        val image = view.findViewById<ImageView>(R.id.cartimage)
        val card = view.findViewById<CardView>(R.id.cartcard)
        val plus = view.findViewById<ImageButton>(R.id.addquantity)
        val remove = view.findViewById<ImageButton>(R.id.removefromcart)
        val minus = view.findViewById<ImageButton>(R.id.removequantity)
        val quantity = view.findViewById<TextView>(R.id.quantity)
    }

    fun onbuy() {
        val data: MutableMap<String, Any> = java.util.HashMap()
        val c = Calendar.getInstance()
        val today = c.time
        data["date"]=today.toString()
        data["paymentmethod"]="By Cash"
        c.add(Calendar.DATE, 3)
        val future = c.time.toString()
        data["dateofdeliver"]=future
        val list: ArrayList<cartitem> = arrayListOf()
        for ( i in productlist){
            list.add(i)
        }
        data["items"]=list
    }


}


