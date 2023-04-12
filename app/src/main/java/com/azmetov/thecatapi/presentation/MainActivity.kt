package com.azmetov.thecatapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.azmetov.thecatapi.data.network.ApiFactory
import com.azmetov.thecatapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

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
        val r = ApiFactory.apiService.search(0, 10)
        Log.d("TAG", r.toString())
    }
}