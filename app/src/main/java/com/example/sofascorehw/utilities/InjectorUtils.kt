package com.example.sofascorehw.utilities

import com.example.sofascorehw.model.AlbumRepository
import com.example.sofascorehw.model.Database
import com.example.sofascorehw.viewmodel.AlbumsViewModelFactory

object InjectorUtils {

    fun provideAlbumsViewModelFactory(): AlbumsViewModelFactory {
        val albumRepository = AlbumRepository.getInstance(Database.getInstance().albumDao)
        return AlbumsViewModelFactory(albumRepository)
    }
}