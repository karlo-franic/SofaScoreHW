package com.example.sofascorehw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.OnFavoriteClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.WeatherCardLayoutBinding
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.ui.favorite.WeatherFavoriteViewModel
import com.example.sofascorehw.ui.search.WeatherViewModel
import java.lang.Math.abs
import java.lang.NullPointerException

class WeatherRecycleAdapter(
    val context: Context,
    val weatherList: ArrayList<WeathersResponse>,
    val onCityClickListener: OnCityClickListener?,
    val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<WeatherRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]

        val parts = weather.latt_long.split(",")
        var north: Double = parts[0].toDouble()
        var south: Double = parts[1].toDouble()
        var north_Int: Int = north.toInt()
        var south_Int: Int = south.toInt()
        var north_Dec: Double = (north - north_Int) * 60
        var south_Dec: Double = (south - south_Int) * 60

        var north_toString: String = ""

        if (north_Int > 0 && south_Int > 0) {
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'N, ${south_Int}°${south_Dec.toInt()}'W"
        } else if (north_Int > 0 && south_Int < 0) {
            south_Int = abs(south_Int)
            south_Dec = abs(south_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'N, ${south_Int}°${south_Dec.toInt()}'E"
        } else if (north_Int < 0 && south_Int > 0) {
            north_Int = abs(north_Int)
            north_Dec = abs(north_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'S, ${south_Int}°${south_Dec.toInt()}'W"
        } else if (north_Int < 0 && south_Int < 0) {
            north_Int = abs(north_Int)
            north_Dec = abs(north_Dec)
            south_Int = abs(south_Int)
            south_Dec = abs(south_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'S, ${south_Int}°${south_Dec.toInt()}'E"
        }



        holder.binding.cityTitle.text = "${weather.title}"
        holder.binding.coordinateTextview.text = "${north_toString}"
        holder.binding.distanceTextview.text = "Distance: 8542 km"
        holder.binding.weatherTemp.text = "23°"
        holder.binding.favoriteImage.setImageResource(R.drawable.ic_star_0)
        holder.binding.weatherIcon.setImageResource(R.drawable.ic_lc)


        holder.itemView.setOnClickListener {
            if (onCityClickListener != null) {
                onCityClickListener.onCityItemClicked(position)
            }
        }

        holder.binding.favoriteImage.setOnClickListener {
            holder.binding.favoriteImage.setImageResource(R.drawable.ic_star_1)

            if (onFavoriteClickListener != null) {
                onFavoriteClickListener.onFavoriteBtnClicked(position)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = WeatherCardLayoutBinding.bind(itemView)
    }
}