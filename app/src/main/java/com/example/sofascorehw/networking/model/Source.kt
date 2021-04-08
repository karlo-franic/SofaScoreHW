package com.example.sofascorehw.networking.model


import com.google.gson.annotations.SerializedName

data class Source(
    val title: String,
    val slug: String,
    val url: String,
    @SerializedName("crawl_rate")
    val crawlRate: Int
)