package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.webkit.internal.ApiFeature
import com.example.weatherapp.repository.WeatherRepository

class WeatherViewModelFactory(private val repository: WeatherRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("Unchecked_Cast")
            return (WeatherViewModel(repository)as T)
        }
        throw IllegalArgumentException("Unknown View model Class")
    }
}