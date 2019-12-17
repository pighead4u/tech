package com.holiday.tech.ui.home

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
class HomeAdapter() : RecyclerView.Adapter<HomeViewHolder>() {

    private val TAG = "HomeAdapter"

    private val data = mutableListOf<HomeVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_item,
                parent,
                false
            )
        )
    }

    fun setData(input: List<HomeVO>) {
        data.addAll(input)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data[position]

        Glide.with(holder.ivGirl.context)
            .load(item.url)
            .into(holder.ivGirl)
    }
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivGirl = itemView.ivGirl
}