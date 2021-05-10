package com.example.sofascorehw.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofascorehw.model.db.WeatherDatabase
import com.example.sofascorehw.model.networking.Network
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeatherRecentWrapper
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.model.shared.specificweather.SpecificWeather
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    val weatherList = MutableLiveData<ArrayList<WeathersResponse>>()
    val weatherSearchList = MutableLiveData<ArrayList<WeathersResponse>>()
    val specificWeatherSearchList = MutableLiveData<ArrayList<SpecificWeather>>()
    val weatherRecentWrapperList = MutableLiveData<WeatherRecentWrapper>()
    val weatherOne = MutableLiveData<SpecificWeather>()


    fun getSearchedWeathers(title: String) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSearchedWeathers(title)
            weatherSearchList.value = weathersResponse as ArrayList<WeathersResponse>
        }
    }

    fun getSearchedWeathersWithSpecifics(title: String) {
        viewModelScope.launch {
            val weatherResult = Network().getService().getSearchedWeathers(title)
            val asyncTasks = weatherResult.map { weather ->
                async { Network().getService().getSpecificWeather(weather.woeid) }
            }
            val responseList = asyncTasks.awaitAll() as ArrayList<SpecificWeather>
            specificWeatherSearchList.value = responseList as ArrayList<SpecificWeather>
            weatherList.value = weatherResult as ArrayList<WeathersResponse>
            weatherRecentWrapperList.value = WeatherRecentWrapper(weatherResult, responseList)
        }
    }

    fun getSpecificWeather(id: Int) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSpecificWeather(id)
            weatherOne.value = weathersResponse
        }
    }

    // RECENT - INSERT
    fun saveRecentWeatherToDb(context: Context, weather: WeathersResponse) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.insertWeather(weather)
        }
    }

    // FAVORITE - INSERT
    fun saveFavoriteWeatherToDb(context: Context, weather: FavoriteWeather) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.insertFavoriteWeather(weather)
        }
    }

    // RECENT - SELECT ALL
    fun getRecentWeatherFromDb(context: Context) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            weatherList.value = db?.weathersDao()?.getAllWeathers() as ArrayList<WeathersResponse>
            val recentResult = db?.weathersDao()?.getAllWeathers() as ArrayList<WeathersResponse>
            val asyncTasks = recentResult.map { weather ->
                async { Network().getService().getSpecificWeather(weather.woeid) }
            }
            if (!asyncTasks.awaitAll().isEmpty()) {
                val responseList = asyncTasks.awaitAll() as ArrayList<SpecificWeather>
                specificWeatherSearchList.value = responseList as ArrayList<SpecificWeather>
                weatherRecentWrapperList.value = WeatherRecentWrapper(recentResult, responseList)
            }
        }
    }

    // RECENT - DELETE
    fun deleteRecentWeatherFromDb(context: Context, weather: WeathersResponse) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteRecentWeather(weather)
        }
    }

    // RECENT - DELETE ALL
    fun deleteAllRecentWeatherFromDb(context: Context) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteAllRecentWeather()
        }
    }

    // FAVORITE - DELETE ALL
    fun deleteAllFavoriteWeatherFromDb(context: Context) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteAllFavoriteWeather()
        }
    }
}