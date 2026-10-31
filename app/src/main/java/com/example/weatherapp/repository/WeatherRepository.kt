package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.remote.weatherApi

open class WeatherRepository(private val WeatherApi: weatherApi)
{
    suspend fun getweather( apikey: String, city: String): WeatherResponse
    {
return WeatherApi.getCurrentWeather(apikey  , city=city)
    }
}
