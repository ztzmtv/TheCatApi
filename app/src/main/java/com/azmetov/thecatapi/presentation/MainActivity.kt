package com.azmetov.thecatapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.azmetov.thecatapi.data.network.client.NetworkClient
import com.azmetov.thecatapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        lifecycleScope.launch {
            getRes()
        }

    }

    private suspend fun getRes() {
        val client: NetworkClient by inject()
        val res = client.getImages(10, 10)
        Log.d("TAG", res.toString())
    }
}