package com.azmetov.thecatapi.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azmetov.thecatapi.R
import com.azmetov.thecatapi.databinding.ImageItemBinding
import com.azmetov.thecatapi.domain.entity.ImageEntity
import com.bumptech.glide.Glide

class ImagesPagingAdapter(
    private val context: Context
) : PagingDataAdapter<ImageEntity, ImagesPagingAdapter.ImagesViewHolder>(ImagesItemDiffCallback) {

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = getItem(position) ?: throw RuntimeException("Entity == null")
        Glide
            .with(context)
            .load(item.url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.image);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesViewHolder(binding)
    }

    class ImagesViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)
}