package com.example.sofascorehw.model.shared.specificweather


import com.example.sofascorehw.model.shared.specificweather.ConsolidatedWeather
import com.example.sofascorehw.model.shared.specificweather.Parent
import com.example.sofascorehw.model.shared.specificweather.Source
import com.google.gson.annotations.SerializedName

data class SpecificWeather(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,
    val time: String,
    @SerializedName("sun_rise")
    val sunRise: String,
    @SerializedName("sun_set")
    val sunSet: String,
    @SerializedName("timezone_name")
    val timezoneName: String,
    val parent: Parent,
    val sources: List<Source>,
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String,
    val timezone: String
)