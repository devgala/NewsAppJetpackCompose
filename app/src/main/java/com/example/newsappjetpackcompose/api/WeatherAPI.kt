package com.example.newsappjetpackcompose.api

import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse
import com.example.newsappjetpackcompose.util.Constants.Companion.WEATHER_API_KEY
import retrofit2.http.GET

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @retrofit2.http.Query("lon")
        lon:Double,
        @retrofit2.http.Query("lat")
        lat:Double,
        @retrofit2.http.Query("appid")
        appid:String = WEATHER_API_KEY,
        @retrofit2.http.Query("units")
        units: String = "metric",
        @retrofit2.http.Query(value = "exclude",encoded = true)
        exclude: String = "minutely,hourly,daily,alerts"
    ):WeatherResponse
}