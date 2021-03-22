package com.example.sofascorehw.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.model.AlbumRepository

class AddViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun addAlbum(album: Album) = albumRepository.addAlbum(album)
}