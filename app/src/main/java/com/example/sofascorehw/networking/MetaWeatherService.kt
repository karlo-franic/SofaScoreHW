package com.example.sofascorehw.networking

import com.example.sofascorehw.networking.model.SpecificWeatherResponse
import com.example.sofascorehw.networking.model.WeathersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MetaWeatherService {

    @GET("api/location/search/{title}")
    suspend fun getSearchedWeathers(@Path("title") title: String): WeathersResponse

    @GET("api/location/{id}")
    suspend fun getSpecificWeather(@Path("id") id: Int): SpecificWeatherResponse
}