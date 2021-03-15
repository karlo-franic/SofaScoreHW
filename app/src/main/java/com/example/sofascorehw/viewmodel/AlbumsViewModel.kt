package com.example.sofascorehw.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.model.AlbumRepository

class AlbumsViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    fun getAlbums() = albumRepository.getAlbums()

    fun addAlbum(album: Album) = albumRepository.addAlbum(album)
}