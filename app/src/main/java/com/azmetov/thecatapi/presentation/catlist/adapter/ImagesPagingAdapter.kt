package com.azmetov.thecatapi.presentation.catlist.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azmetov.thecatapi.databinding.ImageItemBinding
import com.azmetov.thecatapi.domain.entity.CatEntity
import com.azmetov.thecatapi.presentation.imageloader.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class ImagesPagingAdapter(
    private val context: Context,
    private val loader: ImageLoader
) : PagingDataAdapter<CatEntity, ImagesViewHolder>(ImagesItemDiffCallback) {

    private var favorites = mutableListOf<CatEntity>()
    private var adapterScope = CoroutineScope(Dispatchers.Default)
    private var onItemClickListener: ((CatEntity) -> Unit)? = null
    private var onItemLongClickListener: ((CatEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            loader.loadInto(it.url, holder.binding.imageIv, context)
            if (favorites.contains(item)) {
                holder.binding.favoriteIv.setColorFilter(Color.RED)
            }
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            holder.itemView.setOnLongClickListener {
                onItemLongClickListener?.invoke(item)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesViewHolder(binding)
    }

    fun setFavorites(favorites: List<CatEntity>) {
        this.favorites.addAll(favorites)
    }

    fun setItemClickListener(listener: (CatEntity) -> Unit) {
        onItemClickListener = listener
    }

    fun setItemLongClickListener(listener: (CatEntity) -> Unit) {
        onItemLongClickListener = listener
    }

    fun clear() {
        adapterScope.cancel()
        onItemClickListener = null
        onItemLongClickListener = null
    }
}

class ImagesViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)
object ImagesItemDiffCallback : DiffUtil.ItemCallback<CatEntity>() {
    override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem == newItem
    }
}