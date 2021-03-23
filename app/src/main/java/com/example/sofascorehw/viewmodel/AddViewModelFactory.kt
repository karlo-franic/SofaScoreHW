package com.example.sofascorehw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sofascorehw.model.AlbumRepository
import com.example.sofascorehw.ui.add.AddViewModel

class AddViewModelFactory(private val albumRepository: AlbumRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return AddViewModel(albumRepository) as T
    }
}