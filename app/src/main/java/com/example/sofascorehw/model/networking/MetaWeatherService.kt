package com.example.sofascorehw.model.networking

import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.model.shared.specificweather.SpecificWeather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {

    @GET("/api/location/search/")
    suspend fun getSearchedWeathers(@Query("query") search: String): ArrayList<WeathersResponse>

    @GET("/api/location/{id}")
    suspend fun getSpecificWeather(@Path("id") id: Int): SpecificWeather


    @GET("/api/location/search/")
    suspend fun getSearchedFavoriteWeathers(@Query("query") search: String): ArrayList<FavoriteWeather>
}