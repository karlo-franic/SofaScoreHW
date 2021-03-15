package com.example.sofascorehw.model

class Database private constructor() {

    var albumDao = AlbumDao()
        private set

    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Database().also { instance = it }
        }
    }
}