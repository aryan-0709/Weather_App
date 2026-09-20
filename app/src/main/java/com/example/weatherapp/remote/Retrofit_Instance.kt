package com.example.weatherapp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_Instance
{
    private const val Base_URL ="https://api.weatherapi.com/"

    val api : weatherApi by lazy {

        Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(weatherApi::class.java)
    }
}