package com.example.sofascorehw.model.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class SpecificWeatherResponse(
    @PrimaryKey
    @ColumnInfo(name = "specific_weather_id")
    val id: Int,
    val title: String,
    val woeid: Int,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val the_temp: String
) : Serializable