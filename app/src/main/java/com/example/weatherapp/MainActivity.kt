package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.remote.Retrofit_Instance
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.screens.HomeScreen
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = WeatherRepository(
            WeatherApi = Retrofit_Instance.api
        )

        val factory = WeatherViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]
        val apiKey = BuildConfig.WEATHER_API_KEY

        viewModel.loadWeather(apiKey = apiKey, city = "Delhi")

        setContent {
            WeatherAppTheme {
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(uiState) {
                    Log.d(
                        "WeatherUI",
                        "Loading=${uiState.isLoading}, City=${uiState.weather?.location?.name}, Error=${uiState.errorMessage}"
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        uiState = uiState,
                        onSearch = { city ->
                            viewModel.loadWeather(
                                apiKey = apiKey,
                                city = city
                            )
                        }
                    )
                }
            }
        }
    }
}
