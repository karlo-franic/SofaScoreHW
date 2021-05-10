package com.example.sofascorehw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.OnFavoriteClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.ReorderCardLayoutBinding
import com.example.sofascorehw.databinding.WeatherCardLayoutBinding
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeatherFavoriteWrapper
import com.example.sofascorehw.model.shared.WeatherRecentWrapper

class ReorderFavoriteRecycleAdapter(
    val context: Context,
    val weatherFavoriteWrapper: WeatherFavoriteWrapper,
    val onCityClickListener: OnCityClickListener?,
    val onFavoriteClickListener: OnFavoriteClickListener
) : RecyclerView.Adapter<ReorderFavoriteRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.reorder_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return weatherFavoriteWrapper.weatherResponseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherFavoriteWrapper.weatherResponseList[position]
        val specificWeather = weatherFavoriteWrapper.specificWeatherResponseList[position]

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
            south_Int = Math.abs(south_Int)
            south_Dec = Math.abs(south_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'N, ${south_Int}°${south_Dec.toInt()}'E"
        } else if (north_Int < 0 && south_Int > 0) {
            north_Int = Math.abs(north_Int)
            north_Dec = Math.abs(north_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'S, ${south_Int}°${south_Dec.toInt()}'W"
        } else if (north_Int < 0 && south_Int < 0) {
            north_Int = Math.abs(north_Int)
            north_Dec = Math.abs(north_Dec)
            south_Int = Math.abs(south_Int)
            south_Dec = Math.abs(south_Dec)
            north_toString =
                "${north_Int}°${north_Dec.toInt()}'S, ${south_Int}°${south_Dec.toInt()}'E"
        }


        holder.binding.cityTitle.text = "${weather.title}"
        holder.binding.coordinateTextview.text = "${north_toString}"
        holder.binding.distanceTextview.text = "Distance: 1242 km"
        holder.binding.weatherTemp.text = "${specificWeather.consolidatedWeather[0].theTemp.toInt()}°"
        holder.binding.favoriteImage.setImageResource(R.drawable.ic_star_1)

        when (specificWeather.consolidatedWeather[0].weatherStateAbbr) {
            "c" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_c)
            "h" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_h)
            "hc" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_hc)
            "hr" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_hr)
            "lc" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_lc)
            "lr" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_lr)
            "s" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_s)
            "sl" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_sl)
            "sn" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_sn)
            "t" -> holder.binding.weatherIcon.setImageResource(R.drawable.ic_t)
            else -> {
                holder.binding.weatherIcon.setImageResource(R.drawable.ic_lc)
            }
        }


        holder.itemView.setOnClickListener {
            if (onCityClickListener != null) {
                onCityClickListener.onCityItemClicked(position)
            }
        }

        holder.binding.favoriteImage.setOnClickListener {
            holder.binding.favoriteImage.setImageResource(R.drawable.ic_star_0)

            if (onFavoriteClickListener != null) {
                onFavoriteClickListener.onFavoriteBtnClicked(position)
            }
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ReorderCardLayoutBinding.bind(itemView)
    }
}