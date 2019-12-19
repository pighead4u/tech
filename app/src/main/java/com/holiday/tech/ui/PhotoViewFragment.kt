package com.holiday.tech.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.holiday.tech.R
import kotlinx.android.synthetic.main.fragment_photo_view.*

class PhotoViewFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoViewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val url = arguments?.getString("imgUrl") ?: "Ali Connors"


        Glide.with(context!!)
            .load(url)
            .into(photoView)
    }

}
