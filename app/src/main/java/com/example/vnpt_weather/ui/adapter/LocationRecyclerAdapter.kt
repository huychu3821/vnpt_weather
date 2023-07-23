package com.example.vnpt_weather.ui.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vnpt_weather.data.model.location.Location
import com.example.vnpt_weather.databinding.LocationListItemBinding
import java.util.Collections


class LocationRecyclerAdapter(
    private var listener: OnLocationLongPressListener
    )
    : RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder>(){

    private var locationList = ArrayList<Location>()
    private var checkedItems: ArrayList<Boolean> = ArrayList()
    private var isSelectionMode = false

    fun getLocationList() : ArrayList<Location>{
        return locationList
    }

    fun setListener(listener: OnLocationLongPressListener){
        this.listener = listener
    }

    fun setLocationList(locationList: List<Location>){
        this.locationList = ArrayList(locationList)
        this.checkedItems = ArrayList<Boolean>(Collections.nCopies(locationList.size,
            false))
        notifyDataSetChanged()
    }

    interface OnLocationLongPressListener {
        fun onLocationLongPress()
    }

     inner class LocationViewHolder(val binding: LocationListItemBinding) : RecyclerView.ViewHolder(binding.root) {
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        with(holder){
            with(locationList[position]){
                binding.locationItemName.text = this.EnglishName
                binding.locationItemTemp.text = this.Current?.Temperature?.Metric?.Value?.toInt().toString()
                binding.locationItemWeather.text= this.Current?.WeatherText

                if (isSelectionMode) {
                    binding.checkbox.visibility = View.VISIBLE
                    binding.locationItemTemp.visibility = View.GONE
                    binding.tempUnit.visibility = View.GONE
                    binding.locationItemWeather.visibility = View.GONE
                    binding.checkbox.isChecked = checkedItems[position]
                } else {
                    binding.checkbox.visibility = View.GONE
                    binding.locationItemTemp.visibility = View.VISIBLE
                    binding.tempUnit.visibility = View.VISIBLE
                    binding.locationItemWeather.visibility = View.VISIBLE
                }

                holder.itemView.setOnClickListener {
                    if (isSelectionMode) {
                        toggleSelection(position)
                        notifyItemChanged(position)
                    }
                }
                holder.itemView.setOnLongClickListener {
                    isSelectionMode = true
                    toggleSelection(position)
                    notifyDataSetChanged()
                    listener.onLocationLongPress()
                    true
                }

            }
        }
    }

    private fun toggleSelection(position: Int) {
        checkedItems[position] = !checkedItems[position]
    }
    fun exitSelectionMode() {
        isSelectionMode = false
        clearSelections()
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<Int>? {
        val selectedPositions: ArrayList<Int> = ArrayList()
        for (i in checkedItems.indices) {
            if (checkedItems[i]) {
                selectedPositions.add(i)
            }
        }
        return selectedPositions
    }

    fun clearSelections() {
        for (i in checkedItems.indices) {
            checkedItems[i] = false
        }
    }

}