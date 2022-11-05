package com.girrafeecstud.society_safety_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.girrafeecstud.society_safety_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}