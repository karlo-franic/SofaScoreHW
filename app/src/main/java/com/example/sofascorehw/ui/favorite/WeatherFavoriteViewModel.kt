package com.example.sofascorehw.ui.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofascorehw.model.db.WeatherDatabase
import com.example.sofascorehw.model.networking.Network
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.SpecificWeatherResponse
import com.example.sofascorehw.model.shared.WeathersResponse
import kotlinx.coroutines.launch

class WeatherFavoriteViewModel : ViewModel() {

    val weatherList = MutableLiveData<ArrayList<WeathersResponse>>()
    val weatherFavoriteList = MutableLiveData<ArrayList<FavoriteWeather>>()
    val weatherOne = MutableLiveData<SpecificWeatherResponse>()

    init {

    }

    fun getSearchedWeathers(title: String) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSearchedWeathers(title)
            weatherList.value = weathersResponse as ArrayList<WeathersResponse>
        }
    }

    fun getSearchedFavoriteWeathers(title: String) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSearchedFavoriteWeathers(title)
            weatherFavoriteList.value = weathersResponse as ArrayList<FavoriteWeather>
        }
    }

    fun getInitWeathers(): MutableLiveData<ArrayList<WeathersResponse>> {
        return weatherList
    }

    fun getInitFavoriteWeathers(): MutableLiveData<ArrayList<FavoriteWeather>> {
        return weatherFavoriteList
    }

    fun getSpecificWeather(id: Int) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSpecificWeather(id)
            weatherOne.value = weathersResponse
        }
    }

    fun getInitSpecificWeather(): MutableLiveData<SpecificWeatherResponse> {
        return weatherOne
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
        }
    }

    // FAVORITE - SELECT ALL
    fun getFavoriteWeatherFromDb(context: Context) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            weatherFavoriteList.value =
                db?.weathersDao()?.getAllFavoriteWeathers() as ArrayList<FavoriteWeather>
        }
    }

    // RECENT - DELETE
    fun deleteRecentWeatherFromDb(context: Context, weather: WeathersResponse) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteRecentWeather(weather)
        }
    }

    // FAVORITE - DELETE
    fun deleteFavoriteWeatherFromDb(context: Context, weather: FavoriteWeather) {
        viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            db?.weathersDao()?.deleteFavoriteWeather(weather)
        }
    }

    // RECENT - COUNT()
    fun sizeOfRecentFromDb(context: Context): Int {
        var size: Int = 0
        val v = viewModelScope.launch {
            val db = WeatherDatabase.getDatabase(context)
            if (db?.weathersDao()?.sizeRecentWeather() != null) {
                size = db?.weathersDao()?.sizeRecentWeather()
            }
        }
        return size
    }
}