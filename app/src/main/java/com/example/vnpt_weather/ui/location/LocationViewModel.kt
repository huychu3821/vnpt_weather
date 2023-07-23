package com.example.vnpt_weather.ui.location

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vnpt_weather.data.model.current.Current
import com.example.vnpt_weather.data.model.current.Metric
import com.example.vnpt_weather.data.model.current.Temperature
import com.example.vnpt_weather.data.model.location.Location

class LocationViewModel : ViewModel(){
    private var locationLiveData = MutableLiveData<List<Location>>()
    private var intent : Intent ?= null
    fun getRegisteredLocation() {
        val location1 = Location("Hanoi", Current(Temperature(Metric(30.0)),"Hầu hết trời nắng đẹp"))
        val location2 = Location("HCM", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        val location3 = Location("Halong", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        val location4 = Location("Danang", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        val location5 = Location("Hue", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        val location6 = Location("Hoian", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        val location7 = Location("BinhDuong", Current(Temperature(Metric(30.0)),"Nắng đẹp"))
        locationLiveData.value = listOf(location1,location2, location3, location4, location5,location6, location7)
    }
    fun observeLocationLiveData() : LiveData<List<Location>> {
        return locationLiveData
    }

    fun backToMainMenu(activity: Activity) {
        activity.finish()
    }



}
