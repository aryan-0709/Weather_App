package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Current
    (
        @SerializedName("last_updated_epoch")
            val lastUpdatedEpoch: Long,

        @SerializedName("last_updated")
        val lastUpdated: String,

        @SerializedName("temp_c")
            val tempC: Double,

        @SerializedName("temp_f")
            val tempF: Double,

        @SerializedName("is_day")
            val isDay: Int,

            val condition: Condition,

        @SerializedName("wind_kph")
        val windKph: Double,

        @SerializedName("wind_degree")
        val windDegree: Int,

        @SerializedName("wind_dir")
        val windDir: String,

        @SerializedName("pressure_mb")
        val pressureMb: Double,

        val humidity: Int,

        val cloud: Int,

        @SerializedName("feelslike_c")
        val feelsLikeC: Double,

        @SerializedName("vis_km")
        val visibilityKm: Double,

        val uv: Double,

        @SerializedName("chance_of_rain")
        val chanceOfRain: Int)