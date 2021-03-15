package com.example.sofascorehw.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sofascorehw.R
import com.example.sofascorehw.utilities.InjectorUtils
import com.example.sofascorehw.viewmodel.AlbumsViewModel

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)

        val textView_albums: TextView = view.findViewById(R.id.textView_albums)

        val factory = InjectorUtils.provideAlbumsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(AlbumsViewModel::class.java)

        viewModel.getAlbums().observe(this, Observer { albums ->
            val stringBuilder = StringBuilder()
            albums.forEach { album ->
                stringBuilder.append("$album\n\n")
            }
            textView_albums.text = stringBuilder.toString()
        })

        return view
    }


}