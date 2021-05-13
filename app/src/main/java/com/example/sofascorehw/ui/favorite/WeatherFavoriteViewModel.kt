package com.example.sofascorehw.ui.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofascorehw.model.db.WeatherDatabase
import com.example.sofascorehw.model.networking.Network
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeatherFavoriteWrapper
import com.example.sofascorehw.model.shared.WeatherRecentWrapper
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.model.shared.specificweather.SpecificWeather
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class WeatherFavoriteViewModel : ViewModel() {

    val weatherFavoriteList = MutableLiveData<ArrayList<FavoriteWeather>>()
    val specificWeatherFavoriteList = MutableLiveData<ArrayList<SpecificWeather>>()
    val weatherFavoriteWrapperList = MutableLiveData<WeatherFavoriteWrapper>()
    val weatherOne = MutableLiveData<SpecificWeather>()


    fun getInitFavoriteWeathers(): MutableLiveData<ArrayList<FavoriteWeather>> {
        return weatherFavoriteList
    }

    fun getSpecificWeather(id: Int) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSpecificWeather(id)
            weatherOne.value = weathersResponse
        }
    }

    // FAVORITE - INSERT
    fun saveFavoriteWeatherToDb(context: Context, weather: FavoriteWeather) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.insertFavoriteWeather(weather)
        }
    }

    // FAVORITE - SELECT ALL
    fun getFavoriteWeatherFromDb(context: Context) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            weatherFavoriteList.value =
                db?.weathersDao()?.getAllFavoriteWeathers() as ArrayList<FavoriteWeather>
            val favoriteResult =
                db?.weathersDao()?.getAllFavoriteWeathers() as ArrayList<FavoriteWeather>
            val asyncTasks = favoriteResult.map { weather ->
                async { Network().getService().getSpecificWeather(weather.woeid) }
            }
            if (!asyncTasks.awaitAll().isEmpty()) {
                val responseList = asyncTasks.awaitAll() as ArrayList<SpecificWeather>
                specificWeatherFavoriteList.value = responseList as ArrayList<SpecificWeather>
                weatherFavoriteWrapperList.value =
                    WeatherFavoriteWrapper(favoriteResult, responseList)
            }
        }
    }

    // FAVORITE - DELETE
    fun deleteFavoriteWeatherFromDb(context: Context, weather: FavoriteWeather) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteFavoriteWeather(weather)
        }
    }

    // FAVORITE - UPDATE
    fun updateFavoriteWeatherFromDb(context: Context, weather: FavoriteWeather) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.updateFavoriteOrder(weather)
        }
    }
}