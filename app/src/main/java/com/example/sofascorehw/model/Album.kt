package com.example.sofascorehw.model

class Album(
    val name: String,
    val band: String,
    val genre: String,
    val song_count: String,
    val single: String
) {

    override fun toString(): String {
        return "$band : $name ($genre) - $single, $song_count"
    }
}