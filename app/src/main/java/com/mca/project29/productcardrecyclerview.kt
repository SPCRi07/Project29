package com.mca.project29

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.mca.project29.R
import com.mca.project29.dataModel.Product
import com.squareup.picasso.Picasso

class productcardrecyclerview(context: Context,arrayList: ArrayList<Product>):
    RecyclerView.Adapter<productcardrecyclerview.ViewHolder>()
{
    val con =context
    val productlist =arrayList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(con).inflate(R.layout.productlistcard,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.name.setText(productlist.get(position).name)
        holder.priceclose.setText(productlist[position].priceclosed)
        holder.discount.setText(productlist[position].discount)
        holder.rating.setText(productlist[position].rating)
        Picasso.get().load(productlist[position].image).into(holder.image)
        holder.price.setText(productlist[position].price)
       holder.card.setOnClickListener {

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
    }



}