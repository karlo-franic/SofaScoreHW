package com.example.sofascorehw.model

class AlbumRepository private constructor(private val albumDao: AlbumDao) {
    fun addAlbum(album: Album) {
        albumDao.addAlbum(album)
    }

    fun getAlbums() = albumDao.getAlbums()

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(albumDao: AlbumDao) =
            instance ?: synchronized(this) {
                instance ?: AlbumRepository(albumDao).also { instance = it }
            }
    }
}