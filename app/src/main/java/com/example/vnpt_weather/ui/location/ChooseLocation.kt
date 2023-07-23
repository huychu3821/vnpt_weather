package com.example.vnpt_weather.ui.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vnpt_weather.databinding.ActivityChooseLocationBinding
import com.example.vnpt_weather.ui.adapter.LocationRecyclerAdapter

class ChooseLocation : AppCompatActivity(), LocationRecyclerAdapter.OnLocationLongPressListener {
    private lateinit var binding: ActivityChooseLocationBinding
    private lateinit var viewModel: LocationViewModel
    private lateinit var locationAdapter : LocationRecyclerAdapter
    private var isSelectionMode : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        viewModel.getRegisteredLocation()
        viewModel.observeLocationLiveData().observe(this, Observer { locationList ->
            locationAdapter.setLocationList(locationList)
        })

        binding.backButton.setOnClickListener{
            viewModel.backToMainMenu(this)
        }

        binding.deleteButton.setOnClickListener{
            var lList = locationAdapter.getLocationList()
            var selected = locationAdapter.getSelectedItems()
            if (selected?.isNotEmpty() == true){
                for(i in selected){
                    lList.removeAt(i)
                    locationAdapter.setLocationList(lList)
                }
            }
            else{
                Toast.makeText(this, "Bạn không xóa thành phố nào cả", Toast.LENGTH_SHORT).show()
            }
            isSelectionMode = false
            locationAdapter.exitSelectionMode()
            updateUI()
        }
    }

    private fun prepareRecyclerView() {
        locationAdapter = LocationRecyclerAdapter(this)
        locationAdapter.setListener(this)
        binding.cityRView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = locationAdapter
        }
    }

    override fun onBackPressed() {
        if (isSelectionMode){
            isSelectionMode = false
            locationAdapter.exitSelectionMode()
            updateUI()
        }
        else{
            super.onBackPressed()
        }
    }

    private fun updateUI() {
        if (isSelectionMode) {
            binding.fab.hide()
            binding.deleteBar.visibility = View.VISIBLE
        } else {
            binding.fab.show()
            binding.deleteBar.visibility = View.GONE
        }
    }

    override fun onLocationLongPress() {
        if(!isSelectionMode){
            isSelectionMode = true
            updateUI()
        }
    }
}