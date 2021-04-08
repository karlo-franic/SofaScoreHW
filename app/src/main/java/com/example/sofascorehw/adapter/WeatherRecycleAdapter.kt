package com.example.sofascorehw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sofascorehw.OnAlbumClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.WeatherCardLayoutBinding
import com.example.sofascorehw.networking.model.WeathersResponse
import com.squareup.picasso.Picasso

class WeatherRecycleAdapter(
    val context: Context,
    val weatherList: ArrayList<WeathersResponse>,
    val onAlbumClickListener: OnAlbumClickListener?
) : RecyclerView.Adapter<WeatherRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherRecycleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(com.example.sofascorehw.R.layout.weather_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherRecycleAdapter.ViewHolder, position: Int) {
        val weather = weatherList[position]

        holder.binding.cityTitle.text = "${weather.title}"
        holder.binding.coordinateTextview.text = "37°47′N, 122°25′W"
        holder.binding.distanceTextview.text = "Distance: 8542 km"
        holder.binding.weatherTemp.text = "23°"
        holder.binding.favoriteImage.setImageResource(R.drawable.ic_star_0)
        holder.binding.weatherIcon.setImageResource(R.drawable.ic_lc)
/*
        Picasso.get().load(images[position])
            .resize(100, 100)
            .centerCrop()
            .placeholder(com.example.sofascorehw.R.drawable.coffeeresized)
            .error(com.example.sofascorehw.R.drawable.coffeeresized)
            .into(holder.itemImage)
*/

        holder.itemView.setOnClickListener {
            if (onAlbumClickListener != null) {
                onAlbumClickListener.onAlbumItemClicked(position)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = WeatherCardLayoutBinding.bind(itemView)
      /*
        var itemImage: ImageView
        var itemTitle: TextView
        var itemBand: TextView

        init {
            itemImage = itemView.findViewById(com.example.sofascorehw.R.id.item_image)
            itemTitle = itemView.findViewById(com.example.sofascorehw.R.id.item_title)
            itemBand = itemView.findViewById(com.example.sofascorehw.R.id.item_band)
        */
    }
}