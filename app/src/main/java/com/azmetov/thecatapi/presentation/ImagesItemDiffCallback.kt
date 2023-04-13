package com.azmetov.thecatapi.presentation

import androidx.recyclerview.widget.DiffUtil
import com.azmetov.thecatapi.domain.entity.ImageEntity

object ImagesItemDiffCallback : DiffUtil.ItemCallback<ImageEntity>() {
    override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem == newItem
    }
}