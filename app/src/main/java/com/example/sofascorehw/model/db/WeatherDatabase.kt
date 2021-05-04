package com.example.sofascorehw.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse

@Database(entities = [WeathersResponse::class, FavoriteWeather::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weathersDao(): WeathersDao

    companion object {
        private var instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase? {
            if (instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "WeatherRoom.db"
            ).build()
    }
}