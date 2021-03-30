package com.example.sofascorehw.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofascorehw.model.AlbumRepository

class AlbumsViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getAlbums() = albumRepository.getAlbums()
}