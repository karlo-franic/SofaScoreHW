package com.example.sofascorehw.model.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class WeathersResponse(
    @PrimaryKey
    @ColumnInfo(name = "weathers_id")
    var id: Int,
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
) : Serializable

@Entity
data class FavoriteWeather(
    @PrimaryKey
    @ColumnInfo(name = "favorite_id")
    val id: Int,
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
) : Serializable