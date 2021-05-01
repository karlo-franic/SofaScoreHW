package com.example.sofascorehw.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sofascorehw.model.shared.WeathersResponse

@Dao
interface WeathersDao {

    @Query("SELECT * FROM weathersresponse")
    fun getAllWeathers(): List<WeathersResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeather(weather: WeathersResponse)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllWeathers(weatherList: List<WeathersResponse>) {
        for (weather in weatherList) {
            insertWeather(weather)
        }
    }
}