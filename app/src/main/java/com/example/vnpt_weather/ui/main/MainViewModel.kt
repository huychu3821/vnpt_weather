package com.example.vnpt_weather.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.vnpt_weather.ui.location.ChooseLocation

class MainViewModel : ViewModel() {

    private var intent: Intent? = null

    fun startLocationIntent(context: Context) {
        if (intent == null) {
            intent = Intent(context, ChooseLocation::class.java)
        }
        context.startActivity(intent)
    }

}