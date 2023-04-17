package com.azmetov.thecatapi.presentation

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azmetov.thecatapi.databinding.ImageItemBinding
import com.azmetov.thecatapi.domain.entity.CatEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class ImagesPagingAdapter(
    private val context: Context,
    private val loader: ImageLoader
) : PagingDataAdapter<CatEntity, ImagesPagingAdapter.ImagesViewHolder>(ImagesItemDiffCallback) {

    private var onItemClickListener: ((CatEntity) -> Unit)? = null
    private var onItemLongClickListener: ((CatEntity) -> Unit)? = null
    private var favorites = mutableListOf<CatEntity?>()
    private var adapterScope = CoroutineScope(Dispatchers.Default)

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

    fun setFavorites(favorites: List<CatEntity?>) {
        this.favorites.addAll(favorites.filterNotNull())
    }

    fun setItemClickListener(listener: (CatEntity) -> Unit) {
        onItemClickListener = listener
    }

    fun setItemLongClickListener(listener: (CatEntity) -> Unit) {
        onItemLongClickListener = listener
    }

    fun clear() {
        onItemClickListener = null
        onItemLongClickListener = null
        adapterScope.cancel()
    }

    class ImagesViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)
}