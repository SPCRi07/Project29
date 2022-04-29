package com.mca.project29.mainScreens.homeFragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mca.project29.R
import com.mca.project29.mainScreens.ProductPage.Productpagefragment
import com.squareup.picasso.Picasso

class homecardrecyclerview(
    context: Context,
    arrayList: MutableList<String>,
    arrayimage: MutableList<String>,
    fg: FragmentManager
):
    RecyclerView.Adapter<homecardrecyclerview.ViewHolder>()
{

    val con =context
    val arstring =arrayList
    val arimage =arrayimage
    val fgmanager=fg
    val arname= arrayOf("Flour","Beverages","Fruits","Masala","snacks","Cookies","Dryfruits",
                        "Vegetables")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(con).inflate(R.layout.homecardlist,parent,false)
        return ViewHolder(view)

    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load(arimage[position]).into(holder.img)
        holder.txt.text=arstring[position]
        holder.card.setOnClickListener {
            val fragment = Productpagefragment.newInstance(arname[position])
            val fram = fgmanager.beginTransaction()
            fram.replace(R.id.mainfragment,fragment)
            fram.isAddToBackStackAllowed
            fram.commit()
        }


    }

    override fun getItemCount(): Int {
        return arimage.size

    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val img: ImageView =view.findViewById(R.id.homecardimage)
        val txt: TextView =view.findViewById(R.id.homecardtext)
        val card: CardView =view.findViewById(R.id.homecard)
    }



}