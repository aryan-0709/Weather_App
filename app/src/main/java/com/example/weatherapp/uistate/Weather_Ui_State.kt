package com.example.weatherapp.uistate

import com.example.weatherapp.model.WeatherResponse

data class WeatherUiState(
    val isLoading: Boolean=false,
    val weather: WeatherResponse?= null,
    val errorMessage: String?= null
)