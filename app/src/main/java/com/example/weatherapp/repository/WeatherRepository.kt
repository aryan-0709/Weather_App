package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.remote.weatherApi

open class WeatherRepository(private val WeatherApi: weatherApi)
{
    suspend fun getweather(apikey: String, city: String): WeatherResponse
    {
        return WeatherApi.getForecast(apiKey = apikey, city = city, days = 3)
    }

    suspend fun getForecast(apiKey: String, city: String, days: Int = 3): WeatherResponse
    {
        return WeatherApi.getForecast(apiKey = apiKey, city = city, days = days)
    }
}
