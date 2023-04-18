package com.azmetov.thecatapi.presentation.imageloader

import android.content.Context
import android.widget.ImageView
import com.azmetov.thecatapi.R
import com.bumptech.glide.Glide

interface ImageLoader {
    fun loadInto(url: String, view: ImageView, context: Context)
}

class GlideLoader : ImageLoader {
    override fun loadInto(url: String, view: ImageView, context: Context) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(PLACEHOLDER_ID)
            .into(view);
    }

    companion object {
        private const val PLACEHOLDER_ID = R.drawable.ic_launcher_background
    }
}