package com.holiday.tech.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.holiday.tech.R
import com.holiday.tech.model.HomeVO
import kotlinx.android.synthetic.main.home_item.view.*

/**
 * 作者：pighead4u
 * 时间：2019-12-17
 * 描述：
 **/
class HomeAdapter(val data: List<HomeVO>) : RecyclerView.Adapter<HomeViewHolder>() {

    private val TAG = "HomeAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data[position]
        Log.d(TAG, item.url)
        Glide.with(holder.ivGirl.context)
            .load(item.url)
            .into(holder.ivGirl)
    }
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivGirl = itemView.ivGirl
}