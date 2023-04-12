package com.azmetov.thecatapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.azmetov.thecatapi.BuildConfig
import com.azmetov.thecatapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myApiKey: String = BuildConfig.API_KEY
        Log.d("TAG", myApiKey)
    }
}