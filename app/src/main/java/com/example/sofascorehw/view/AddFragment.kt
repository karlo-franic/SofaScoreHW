package com.example.sofascorehw.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sofascorehw.R
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.utilities.InjectorUtils
import com.example.sofascorehw.viewmodel.AlbumsViewModel


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)

        val factory = InjectorUtils.provideAlbumsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(AlbumsViewModel::class.java)

        val button_add_album: Button = view.findViewById(R.id.button_add_album)

        val editText_album: EditText = view.findViewById(R.id.editText_album)
        val editText_band: EditText = view.findViewById(R.id.editText_band)
        val editText_genre: EditText = view.findViewById(R.id.editText_genre)
        val editText_single: EditText = view.findViewById(R.id.editText_single)
        val editText_count: EditText = view.findViewById(R.id.editText_count)

        button_add_album.setOnClickListener {
            val album = Album(
                editText_album.text.toString(),
                editText_band.text.toString(),
                editText_genre.text.toString(),
                editText_single.text.toString(),
                editText_count.text.toString()
            )
            viewModel.addAlbum(album)
            editText_album.setText("")
            editText_band.setText("")
            editText_genre.setText("")
            editText_single.setText("")
            editText_count.setText("")
        }

        return view
    }


}