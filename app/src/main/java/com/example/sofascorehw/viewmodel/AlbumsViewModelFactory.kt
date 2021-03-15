package com.example.sofascorehw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sofascorehw.model.AlbumRepository

class AlbumsViewModelFactory(private val albumRepository: AlbumRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel(albumRepository) as T
    }
}