package com.azmetov.thecatapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.azmetov.thecatapi.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ImageViewModel by viewModel()
    private val adapter: ImagesPagingAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.recyclerView.adapter = adapter

        adapter.setItemClickListener {
            viewModel.addToFavorites(it)
        }

        adapter.setItemLongClickListener {
            viewModel.deleteFavorite(it)
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearListeners()
    }
}