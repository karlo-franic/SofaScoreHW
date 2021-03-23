package com.example.sofascorehw.ui.add

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.model.AlbumRepository
import com.example.sofascorehw.model.Genre

class AddViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun addAlbum(album: Album) = albumRepository.addAlbum(album)

}