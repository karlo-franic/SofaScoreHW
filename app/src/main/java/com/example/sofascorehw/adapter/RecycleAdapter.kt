package com.example.sofascorehw.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnAlbumClickListener
import com.example.sofascorehw.ui.albums.AlbumsViewModel
import com.example.sofascorehw.utilities.InjectorUtils
import com.squareup.picasso.Picasso

class RecycleAdapter(var titles: List<String>, var band_name: List<String>, var images: List<String>, val onAlbumClickListener: OnAlbumClickListener?): RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(com.example.sofascorehw.R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: RecycleAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemBand.text = band_name[position]
   /*     holder.itemImage.load(images[position]/*com.example.sofascorehw.R.drawable.coffeeresized*/) {
            placeholder(com.example.sofascorehw.R.drawable.coffeeresized)
            error(com.example.sofascorehw.R.drawable.coffeeresized)
        } */
        Picasso.get().load(images[position])
                .resize(100, 100)
                .centerCrop()
                .placeholder(com.example.sofascorehw.R.drawable.coffeeresized)
                .error(com.example.sofascorehw.R.drawable.coffeeresized)
                .into(holder.itemImage)


        holder.itemView.setOnClickListener {
            if (onAlbumClickListener != null) {
                onAlbumClickListener.onAlbumItemClicked(position)
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemBand: TextView

        init {
            itemImage = itemView.findViewById(com.example.sofascorehw.R.id.item_image)
            itemTitle = itemView.findViewById(com.example.sofascorehw.R.id.item_title)
            itemBand = itemView.findViewById(com.example.sofascorehw.R.id.item_band)
        }
    }
}