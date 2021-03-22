package com.example.sofascorehw.model

import android.widget.RadioButton

class Album(
    val name: String,
    val band: String,
    val song_count: String,
    val single: String,
    val genre: Genre
) {

    override fun toString(): String {
        return "$band : $name ($genre) - $single, $song_count"
    }
}