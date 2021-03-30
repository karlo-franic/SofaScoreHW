package com.example.sofascorehw.model

class Album(
    val name: String,
    val band: String,
    val song_count: String,
    val single: String,
    val year: String,
    val country: String,
    val city: String,
    val sold: String,
    val genre: Genre,
    val radio_btn: String,
    val img: String
) {

    override fun toString(): String {
        return "$band : $name ($genre) - $single, $song_count /$radio_btn/ ($year)"
    }
}