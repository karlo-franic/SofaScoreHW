package com.example.sofascorehw.model.shared.specificweather


import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(
    val id: Long,
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,
    val created: String,
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("min_temp")
    val minTemp: Double,
    @SerializedName("max_temp")
    val maxTemp: Double,
    @SerializedName("the_temp")
    val theTemp: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("air_pressure")
    val airPressure: Double,
    val humidity: Int,
    val visibility: Double,
    val predictability: Int
)