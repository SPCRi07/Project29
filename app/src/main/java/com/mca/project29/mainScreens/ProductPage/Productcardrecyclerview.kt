package com.mca.project29.mainScreens.ProductPage

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mca.project29.R
import com.mca.project29.dataModel.InsertRecord
import com.mca.project29.dataModel.Product
import com.mca.project29.dataModel.insertintocart
import com.mca.project29.dataModel.newProduct
import com.squareup.picasso.Picasso

class productcardrecyclerview(context: Context, arrayList: ArrayList<newProduct>, userid: String):
    RecyclerView.Adapter<productcardrecyclerview.ViewHolder>()
{
    val con =context
    val productlist =arrayList
    val uid=userid


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(con).inflate(R.layout.productlistcard,parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dis=productlist[position].discount + "%";
        holder.name.text = productlist[position].name
        holder.priceclose.text=(productlist[position].priceclosed)
        holder.discount.text=(dis)
        holder.rating.text=(productlist[position].rating)
        Picasso.get().load(productlist[position].image).into(holder.image)
        holder.price.text=(productlist[position].price)
        holder.priceclose.paintFlags = holder.priceclose.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
       holder.addtocart.setOnClickListener {
           insertintocart(productlist[position].name.toString(),productlist[position].image.toString(),productlist[position].price.toString(),uid)
           holder.addtocart.text="Item Added"
           }
   }

    override fun getItemCount(): Int {
      return productlist.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name=view.findViewById<MaterialTextView>(R.id.namecarttext)
        val price=view.findViewById<MaterialTextView>(R.id.canpricenow)
        val priceclose=view.findViewById<MaterialTextView>(R.id.canprice)
        val discount=view.findViewById<MaterialTextView>(R.id.pricediscount)
        val rating=view.findViewById<MaterialTextView>(R.id.productrating)
        val image=view.findViewById<ImageView>(R.id.productimage)
        val card=view.findViewById<CardView>(R.id.productcard)
        val addtocart=view.findViewById<Button>(R.id.addtocart)
    }



}