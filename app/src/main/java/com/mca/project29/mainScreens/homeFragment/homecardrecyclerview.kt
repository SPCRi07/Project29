package com.mca.project29.mainScreens.homeFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.mca.project29.R
import com.mca.project29.mainScreens.productPage
import com.squareup.picasso.Picasso

class homecardrecyclerview(context: Context, arrayList: MutableList<String>, arrayimage: MutableList<String>):
    RecyclerView.Adapter<homecardrecyclerview.ViewHolder>()
{
    val con =context
    val arstring =arrayList
    val arimage =arrayimage
    val arname= arrayOf("Flour","Beverages","Fruits","Masala","snacks","Cookies","Dryfruits",
                        "Vegetables")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(con).inflate(R.layout.homecardlist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(arimage[position]).into(holder.img)
        holder.txt.text=arstring[position]
        holder.card.setOnClickListener {
            val intent=Intent(con, productPage::class.java)
            intent.putExtra("product",arname[position])
            con.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return arimage.size

    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val img=view.findViewById<ImageView>(R.id.homecardimage)
        val txt=view.findViewById<TextView>(R.id.homecardtext)
        val card=view.findViewById<CardView>(R.id.homecard)
    }



}