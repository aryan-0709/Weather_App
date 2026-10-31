package com.example.weatherapp.viewmodel

import com.example.weatherapp.remote.Retrofit_Instance
import com.example.weatherapp.repository.WeatherRepository

class Weather_View_Model: WeatherRepository(
    WeatherApi = Retrofit_Instance.api
)

