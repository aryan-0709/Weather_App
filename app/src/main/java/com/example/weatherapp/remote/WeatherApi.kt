package com.example.weatherapp.remote

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi
{
    @GET("v1/current.json")
    suspend fun getCurrentWeather(
        @Query("Key")apiKey: String,
        @Query("q") city:String
    ): WeatherResponse
}