package com.example.sofascorehw.networking.model

import java.io.Serializable

data class WeathersResponse(
    val id: Int,
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
) : Serializable
