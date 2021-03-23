package com.example.sofascorehw.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.FragmentAlbumsBinding
import com.example.sofascorehw.utilities.InjectorUtils

class AlbumsFragment : Fragment() {

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

        albumsViewModel.getAlbums().observe(this, Observer { albums ->
            val stringBuilder = StringBuilder()
            albums.forEach { album ->
                stringBuilder.append("$album\n\n")
            }
            binding.textViewAlbums.text = stringBuilder.toString()
        })

        return view
    }
}