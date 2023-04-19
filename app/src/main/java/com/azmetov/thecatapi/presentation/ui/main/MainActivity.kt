package com.azmetov.thecatapi.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.azmetov.thecatapi.R
import com.azmetov.thecatapi.databinding.ActivityMainBinding
import com.azmetov.thecatapi.presentation.ui.catlist.fragment.CatListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CatListFragment>(R.id.container)
            }
        }
    }

}