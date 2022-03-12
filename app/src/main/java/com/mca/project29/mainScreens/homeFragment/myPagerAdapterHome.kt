package com.mca.project29.mainScreens.homeFragment

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.mca.project29.R
import com.squareup.picasso.Picasso


class myPagerAdapterHome(context: Context, images: MutableList<String>) :
    PagerAdapter() {
    var con: Context = context
    var img: MutableList<String> = images
    var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return img.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.viewpagerhomesceenitem, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.imgview) as ImageView
        Picasso.get()
            .load(img[position])
             .into(imageView)
       // Log.d(TAG, "instantiateItem: "+img[position])
        container.addView(itemView)

        imageView.setOnClickListener {

            Toast.makeText(con, "you clicked image " + (position + 1), Toast.LENGTH_LONG)
                .show()

        }
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}