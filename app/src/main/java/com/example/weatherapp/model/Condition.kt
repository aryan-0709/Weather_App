package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

@DrawableRes
fun getWeatherIcons(code: Int): Int{
    return when (code){
        1000 -> R.drawable.sunny_weather
        1003 -> R.drawable.partly_cloud_weather
        1006,1009 -> R.drawable.over_cast_weather
        1087-> R.drawable.thunder_storm_weather
        in 1150..1201 ->R.drawable.rainy_weather


else -> R.drawable.cloudy_weather
    }
}


fun getWeatherDescription(code: Int): String {
    return when (code) {
        1000 -> "Clear skies with plenty of sunshine. Enjoy the bright weather!"

        1003 -> "Some clouds are covering the sky, but the weather remains pleasant."

        1006 -> "Clouds are covering the sky. Expect limited sunshine."

        1009 -> "The sky is completely overcast with thick cloud cover."

        1087 -> "Thunderstorms are expected. Stay safe indoors if possible."

        in 1150..1201 -> "Rain is expected. Consider carrying an umbrella!"

        else -> "Keep an eye on the sky. Weather conditions may change."
    }
}