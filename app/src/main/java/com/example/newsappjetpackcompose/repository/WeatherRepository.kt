package com.example.newsappjetpackcompose.repository

import com.example.newsappjetpackcompose.model.weatherModel.WeatherResponse

interface WeatherRepository {
    suspend fun  getWeatherData(longitude:Double,latitude:Double):WeatherResponse
}