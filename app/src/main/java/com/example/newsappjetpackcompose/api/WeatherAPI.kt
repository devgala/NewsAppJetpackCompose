package com.example.newsappjetpackcompose.api

import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse
import com.example.newsappjetpackcompose.util.Constants.Companion.WEATHER_API_KEY
import retrofit2.http.GET

interface WeatherAPI {
    @GET("current.json")
    suspend fun getWeatherData(
      @retrofit2.http.Query("q")
        location:String = "Mumbai",
        @retrofit2.http.Query("key")
        appid:String = WEATHER_API_KEY
    ):WeatherResponse
}