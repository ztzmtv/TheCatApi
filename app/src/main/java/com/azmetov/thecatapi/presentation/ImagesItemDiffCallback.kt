package com.azmetov.thecatapi.presentation

import androidx.recyclerview.widget.DiffUtil
import com.azmetov.thecatapi.domain.entity.CatEntity

object ImagesItemDiffCallback : DiffUtil.ItemCallback<CatEntity>() {
    override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem == newItem
    }
}