package com.example.sofascorehw.model.shared

import com.example.sofascorehw.model.shared.specificweather.SpecificWeather
import java.io.Serializable

data class WeatherRecentWrapper (
    val weatherResponseList: ArrayList<WeathersResponse>,
    val specificWeatherResponseList: ArrayList<SpecificWeather>
) : Serializable

data class WeatherFavoriteWrapper (
    val weatherResponseList: ArrayList<FavoriteWeather>,
    val specificWeatherResponseList: ArrayList<SpecificWeather>
) : Serializable