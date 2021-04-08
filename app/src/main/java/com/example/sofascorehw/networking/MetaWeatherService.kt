package com.example.sofascorehw.networking

import com.example.sofascorehw.networking.model.NewWeathersResponse
import com.example.sofascorehw.networking.model.SpecificWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.sofascorehw.networking.model.WeathersResponse as WeathersResponse

interface MetaWeatherService {

    @GET("/api/location/search/")
    suspend fun getSearchedWeathers(@Query("query") search: String): WeathersResponse

    @GET("/api/location/{id}")
    suspend fun getSpecificWeather(@Path("id") id: Int): SpecificWeatherResponse

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