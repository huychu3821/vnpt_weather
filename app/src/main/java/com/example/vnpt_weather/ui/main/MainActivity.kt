package com.example.vnpt_weather.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.vnpt_weather.databinding.ActivityMainBinding
import com.example.vnpt_weather.ui.location.ChooseLocation
import com.example.vnpt_weather.ui.location.LocationViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.locationSelected.setOnClickListener{
            viewModel.startLocationIntent(this)
        }
    }
}