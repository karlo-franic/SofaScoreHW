package com.example.sofascorehw.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AlbumDao {
    private val albumList = mutableListOf<Album>()
    private val albums = MutableLiveData<List<Album>>()

    init {
        albums.value = albumList
    }

    fun addAlbum(album: Album) {
        albumList.add(album)
        albums.value = albumList
    }

    fun getAlbums() = albums as LiveData<List<Album>>
}