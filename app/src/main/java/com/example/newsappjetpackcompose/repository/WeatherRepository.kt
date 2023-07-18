package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse

interface WeatherRepository {
    suspend fun  getWeatherData(location:String):WeatherResponse
}