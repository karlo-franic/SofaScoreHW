package com.example.sofascorehw.ui.albums

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnAlbumClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.adapter.RecycleAdapter
import com.example.sofascorehw.databinding.FragmentAlbumsBinding
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.utilities.InjectorUtils
import android.content.Intent as Intent

class AlbumsFragment : Fragment(), OnAlbumClickListener {

    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var binding: FragmentAlbumsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_albums, container, false)
        binding = FragmentAlbumsBinding.bind(view)

        val factory = InjectorUtils.provideAlbumsViewModelFactory()
        albumsViewModel = ViewModelProviders.of(this, factory)
            .get(AlbumsViewModel::class.java)

        val all_Albums = mutableListOf<String>()

        val recyc_titles = mutableListOf<String>()
        val recyc_band = mutableListOf<String>()
        val recyc_img = mutableListOf<String>()

        albumsViewModel.getAlbums().observe(this, Observer { albums ->
            albums.forEach { album ->
                all_Albums += album.toString()

                recyc_titles += album.name
                recyc_band += album.band
                recyc_img += album.img
            //    recyc_img += this.getResources().getIdentifier("ic_baseline_settings", "drawable", null)
            }
        })

        binding.albumList.layoutManager = LinearLayoutManager(context)
        binding.albumList.adapter =
            RecycleAdapter(recyc_titles, recyc_band, recyc_img, this)

        return view
    }

    override fun onAlbumItemClicked(position: Int) {

        val all_Albums = mutableListOf<Album>()

        albumsViewModel.getAlbums().observe(this, Observer { albums ->
            albums.forEach { album ->
                all_Albums += album
            }
        })

        val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
        intent.putExtra("name", all_Albums[position].name)
        intent.putExtra("band", all_Albums[position].band)
        intent.putExtra("single", all_Albums[position].single)
        intent.putExtra("country", all_Albums[position].country)
        intent.putExtra("city", all_Albums[position].city)
        intent.putExtra("year", all_Albums[position].year)
        intent.putExtra("song_count", all_Albums[position].song_count)
        intent.putExtra("genre", all_Albums[position].genre)

        startActivity(intent)
    }
}

