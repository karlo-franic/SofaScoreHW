package com.example.sofascorehw.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofascorehw.model.db.WeatherDatabase
import com.example.sofascorehw.model.networking.Network
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.SpecificWeatherResponse
import com.example.sofascorehw.model.shared.WeathersResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val weatherList = MutableLiveData<ArrayList<WeathersResponse>>()
    val weatherFavoriteList = MutableLiveData<ArrayList<FavoriteWeather>>()
    val weatherSearchList = MutableLiveData<ArrayList<WeathersResponse>>()

    //  val weatherOne = MutableLiveData<ArrayList<SpecificWeatherResponse>>()
    val weatherOne = MutableLiveData<SpecificWeatherResponse>()

    init {

    }

    fun getSearchedWeathers(title: String) {
        viewModelScope.launch {
            val weatherResult = async { Network().getService().getSearchedWeathers(title) }
            val weathersResponse = weatherResult.await()
            weatherSearchList.value = weathersResponse as ArrayList<WeathersResponse>
        }
    }

    fun getInitWeathers(): MutableLiveData<ArrayList<WeathersResponse>> {
        return weatherList
    }

    fun getSearchedWeathers(): MutableLiveData<ArrayList<WeathersResponse>> {
        return weatherSearchList
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
            size = db?.weathersDao()?.sizeRecentWeather()!!
        }

        return size
    }
}