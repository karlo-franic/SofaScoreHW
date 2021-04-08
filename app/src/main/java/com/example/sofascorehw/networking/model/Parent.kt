package com.example.sofascorehw.networking.model


import com.google.gson.annotations.SerializedName

data class Parent(
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String
)