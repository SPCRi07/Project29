package com.mca.project29.mainScreens.homeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mca.project29.R

class homecardrecyclerview(context: Context, arrayList: Array<String>, arrayimage: Array<Int>):
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
        holder.img.setImageResource(arimage[position])
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