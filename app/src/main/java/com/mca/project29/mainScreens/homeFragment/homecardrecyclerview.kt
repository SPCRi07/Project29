package com.mca.project29.mainScreens.homeFragment

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mca.project29.R
import com.squareup.picasso.Picasso

class homecardrecyclerview(context: Context, arrayList: MutableList<String>, arrayimage: MutableList<String>):
    RecyclerView.Adapter<homecardrecyclerview.ViewHolder>()
{
    val con =context
    val arstring =arrayList
    val arimage =arrayimage



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view=LayoutInflater.from(con).inflate(R.layout.homecardlist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(arimage[position]).into(holder.img)
        holder.txt.text=arstring[position]
    }

    override fun getItemCount(): Int {
        return arimage.size

    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val img=view.findViewById<ImageView>(R.id.homecardimage)
        val txt=view.findViewById<TextView>(R.id.homecardtext)

    }

}