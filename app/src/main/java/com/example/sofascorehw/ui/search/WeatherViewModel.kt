package com.example.sofascorehw.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofascorehw.networking.Network
import com.example.sofascorehw.networking.model.NewWeathersResponse
import com.example.sofascorehw.networking.model.SpecificWeatherResponse
import com.example.sofascorehw.networking.model.WeathersResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val weatherList = MutableLiveData<ArrayList<WeathersResponse>>()
    lateinit var weatherOne: SpecificWeatherResponse

    init {
        weatherList.value = arrayListOf(
            WeathersResponse(
                0,
                "Zagreb",
                "City",
                851128,
                "45.807259,15.967600"
            ),
            WeathersResponse(
                1,
                "Belfast",
                "City",
                44544,
                "54.595291,-5.934520"
            )
        )
    }

    fun getSearchedWeathers(title: String) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSearchedWeathers(title)
            weatherList.value = weathersResponse as ArrayList<WeathersResponse>
        }
    }


    fun getInitWeathers(): MutableLiveData<ArrayList<WeathersResponse>> {
        return weatherList
    }


    fun getSpecificWeather(id: Int) {
        viewModelScope.launch {
            val weathersResponse = Network().getService().getSpecificWeather(id)
            weatherOne = weathersResponse as SpecificWeatherResponse
        }
    }

}