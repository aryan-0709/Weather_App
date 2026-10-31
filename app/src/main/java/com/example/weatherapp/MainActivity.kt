package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.remote.Retrofit_Instance
import com.example.weatherapp.remote.weatherApi
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.screens.HomeScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository= WeatherRepository (
            WeatherApi = Retrofit_Instance.api
        )

        setContent {
            WeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    HomeScreen()
                }

                lifecycleScope.launch {

                    try {
                        val response = repository.getweather(
                        apikey = "ec2abd820f5b4bd4a9170451261309",
                            city = "Delhi"
                        )

                        Log.d("WEATHER_TEST", "City: ${response.location.name}")
                        Log.d("WEATHER_TEST", "Temperature: ${response.current.tempC}")
                        Log.d("WEATHER_TEST", "Condition: ${response.current.condition.text}")

                    } catch (e: Exception) {
                        Log.e("WEATHER_TEST", "Error: ${e.message}")
                    }
                }


            }
        }
    }
}



