package com.example.sofascorehw.networking.model

import java.io.Serializable

data class SpecificWeatherResponse (
    val id: Int,
    val title: String,
    val woeid: Int,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String
    ) : Serializable