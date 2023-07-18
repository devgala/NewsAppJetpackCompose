package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.api.WeatherRetrofitInstance
import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse

class WeatherRepositoryImpl :WeatherRepository{
    override suspend fun getWeatherData(location:String): WeatherResponse {
        return WeatherRetrofitInstance.api.getWeatherData(location)
    }
}