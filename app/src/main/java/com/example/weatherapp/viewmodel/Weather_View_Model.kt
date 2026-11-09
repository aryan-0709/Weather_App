package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.uistate.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.annotation.meta.When

class WeatherViewModel (
    private val repository: WeatherRepository): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> =_uiState.asStateFlow()


fun loadWeather(apiKey: String, city: String) {
    viewModelScope.launch {
        _uiState.value = WeatherUiState(
            isLoading = true,
            weather = _uiState.value.weather,
            errorMessage = null
        )

        try {
            val response= repository.getweather(apiKey,city)

            Log.d("WeatherDebug", "API request successful: ${response.location.name}")

            _uiState.value = WeatherUiState(
                isLoading = false,
                weather = response,
                errorMessage = null
            )

        } catch (e: Exception) {

            val errorMessage = when {
                e is retrofit2.HttpException && e.code() == 400 ->
                    "City not found. Please check city name."

                e is retrofit2.HttpException && e.code() == 401 ->
                    "Invalid Api Key"

                e is IOException ->
                    "Network error. Please check your internet connection."


                else ->
                    "Something went wrong. Please try again."
            }
            Log.e("WeatherDebug", "API request failed", e)

            _uiState.value = WeatherUiState(
                isLoading = false,
                weather = _uiState.value.weather,
                errorMessage = errorMessage
            )

        }

    }
}

}

