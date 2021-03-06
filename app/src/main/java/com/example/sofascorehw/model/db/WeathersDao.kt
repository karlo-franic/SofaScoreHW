package com.example.sofascorehw.model.db

import androidx.room.*
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse
import java.text.FieldPosition

@Dao
interface WeathersDao {

    @Query("SELECT * FROM weathersresponse ORDER BY date DESC")
    suspend fun getAllWeathers(): List<WeathersResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(weather: WeathersResponse)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllWeathers(weatherList: List<WeathersResponse>) {
        for (weather in weatherList) {
            insertWeather(weather)
        }
    }

    @Query("SELECT COUNT(*) FROM weathersresponse")
    suspend fun sizeRecentWeather(): Int

    @Query("SELECT COUNT(*) FROM favoriteweather")
    suspend fun sizeFavoriteWeather(): Int

    @Query("SELECT * FROM favoriteweather ORDER BY `order`")
    suspend fun getAllFavoriteWeathers(): List<FavoriteWeather>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteWeather(weather: FavoriteWeather)

    @Delete
    suspend fun deleteRecentWeather(weather: WeathersResponse)

    @Delete
    suspend fun deleteFavoriteWeather(weather: FavoriteWeather)

    @Query("DELETE FROM weathersresponse")
    suspend fun deleteAllRecentWeather()

    @Query("DELETE FROM favoriteweather")
    suspend fun deleteAllFavoriteWeather()

    @Update
    suspend fun updateFavoriteOrder(weather: FavoriteWeather)

}