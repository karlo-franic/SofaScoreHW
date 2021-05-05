package com.example.sofascorehw.model.networking

import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.SpecificWeatherResponse
import com.example.sofascorehw.model.shared.WeathersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {

    @GET("/api/location/search/")
    suspend fun getSearchedWeathers(@Query("query") search: String): ArrayList<WeathersResponse>

    @GET("/api/location/{id}")
    suspend fun getSpecificWeather(@Path("id") id: Int): SpecificWeatherResponse


    @GET("/api/location/search/")
    suspend fun getSearchedFavoriteWeathers(@Query("query") search: String): ArrayList<FavoriteWeather>

//    @GET("/api/location/search/?query=san")
//    suspend fun getSearchedWeathers(): WeathersResponse
/*
    companion object {
        operator fun invoke(): MetaWeatherService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.metaweather.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .add
        }
    }

 */
}